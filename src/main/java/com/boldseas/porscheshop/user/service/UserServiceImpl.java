package com.boldseas.porscheshop.user.service;

import com.boldseas.base.client.SmsClient;
import com.boldseas.base.dto.*;
import com.boldseas.porscheshop.common.config.ConstantConfig;
import com.boldseas.porscheshop.common.config.SmsConfig;
import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.common.constant.SendVerificationCodeResultMessage;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.user.LeadsService;
import com.boldseas.porscheshop.user.RedisService;
import com.boldseas.porscheshop.user.UserService;
import com.boldseas.porscheshop.user.beans.RegisterLog;
import com.boldseas.porscheshop.user.beans.Token;
import com.boldseas.porscheshop.user.beans.User;
import com.boldseas.porscheshop.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import com.boldseas.porscheshop.user.repository.RegisterLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private RegisterLogRepository registerLogRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ConstantConfig constantConfig;
    @Autowired
    private SmsClient smsClient;
    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LeadsService leadsService;


    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id);
        return mapper.map(user, UserDTO.class);
    }


    @Override
    public User register(RegisterInputDTO registerInputDTO) {
        User user = userRepository.findById(registerInputDTO.getId());
        user = userRepository.save(mapperUser(user, registerInputDTO));
        RegisterLog registerLog = registerLogRepository.save(mapperRegisterLog(user, registerInputDTO));
        log.info("----update user user=>{}\n registerLog=>{}", user, registerLog);

        return user;
    }


    @Override
    public UserLoginResultDTO login(UserLoginInputDTO userLoginInputDTO) {
        //校验失败次数
        if (isLoginFailOverMaxTimes(userLoginInputDTO.getPhone(), userLoginInputDTO.getIp())) {
            saveLoginLog(null, userLoginInputDTO, UserLoginStatus.LOGIN_FAIL_TOO_MANY_TIMES);
            return new UserLoginResultDTO(UserLoginStatus.LOGIN_FAIL_TOO_MANY_TIMES);
        }

        //校验验证码
        CaptchaCheckInfo captchaCheckInfo = mapper.map(userLoginInputDTO, CaptchaCheckInfo.class);
        RestApiResult<CaptchaCheckResult> checkResult = smsClient.checkCaptcha(smsConfig.getAccessToken(), captchaCheckInfo);
        if (!Objects.equals(checkResult.getCode(), 0) || !checkResult.getData().isSuccess()) {
            saveLoginLog(null, userLoginInputDTO, UserLoginStatus.VERIFICATION_CODE_ERROR);
            redisService.save(PrefixConstant.LOGIN_FAIL_PHONE, userLoginInputDTO.getPhone(), constantConfig.getPhoneLoginFailMinutes());
            redisService.save(PrefixConstant.LOGIN_FAIL_IP, userLoginInputDTO.getIp(), constantConfig.getIpLoginFailMinutes());
            return new UserLoginResultDTO(UserLoginStatus.VERIFICATION_CODE_ERROR);
        }

        //保存用户信息以及保存用户登录记录
        User user = init(userLoginInputDTO);
        saveLoginLog(user.getId(), userLoginInputDTO, UserLoginStatus.LOGIN_SUCCESS);

        //生成用户登录auth_token
        String authToken = UUID.randomUUID().toString();
        Token token = new Token(authToken, new CurrentUserDTO(user.getId(), user.getPhone()), constantConfig.getLoginExpireTime());
        redisService.saveToken(token);

        return new UserLoginResultDTO(authToken, UserLoginStatus.LOGIN_SUCCESS);
    }

    /**
     * 校验登录失败次数是否过多
     *
     * @param phone
     * @param ip
     * @return
     */
    private boolean isLoginFailOverMaxTimes(String phone, String ip) {
        return redisService.isOverMax(PrefixConstant.LOGIN_FAIL_PHONE, phone, constantConfig.getPhoneLoginFailMaxTimes()) ||
                redisService.isOverMax(PrefixConstant.LOGIN_FAIL_IP, ip, constantConfig.getIpLoginFailMaxTimes());
    }

    /**
     * 保存用户登录记录
     *
     * @param userId
     * @param userLoginInputDTO
     * @param userLoginStatus
     */
    private void saveLoginLog(Long userId, UserLoginInputDTO userLoginInputDTO, UserLoginStatus userLoginStatus) {
        UserLoginLogDTO userLoginLogDTO = mapper.map(userLoginInputDTO, UserLoginLogDTO.class);
        userLoginLogDTO.setStatus(userLoginStatus.name());
        userLoginLogDTO.setUserId(userId);
        userLoginLogService.saveUserLoginLog(userLoginLogDTO);
    }

    /**
     * 用户登录初始化用户信息
     *
     * @param userLoginInputDTO
     * @return
     */
    private User init(UserLoginInputDTO userLoginInputDTO) {
        User user = userRepository.findByPhone(userLoginInputDTO.getPhone());
        if (Objects.isNull(user)) {
            user = userRepository.save(new User(userLoginInputDTO.getPhone()));
        }
        return user;
    }

    private User mapperUser(User oldUser, RegisterInputDTO registerInputDTO) {
        User user = mapper.map(registerInputDTO, User.class);
        user.setCreateDate(oldUser.getCreateDate());
        user.setPhone(oldUser.getPhone());
        return user;
    }

    private RegisterLog mapperRegisterLog(User user, RegisterInputDTO registerInputDTO) {
        RegisterLog registerLog = mapper.map(user, RegisterLog.class);
        registerLog.setId(null);
        registerLog.setIntention(registerInputDTO.getIntention());
        registerLog.setSource(userProvider.getUserSource());
        return registerLog;
    }

    @Override
    public UserLoginValidationResultDTO loginValidation(UserLoginValidationInputDTO userLoginValidationInputDTO) {
        if (isLoginValidationFailOverMaxTimes(userLoginValidationInputDTO.getIp())) {
            return new UserLoginValidationResultDTO(LoginValidationStatus.VALIDATION_FAIL_TOO_MANY_TIMES);
        }

        Map<String, String> tokenEntries = redisService.getMapAndResetExpire(PrefixConstant.LOGIN_AUTH_TOKEN, userLoginValidationInputDTO.getAuthToken(), constantConfig.getLoginExpireTime());
        if (tokenEntries.size() <= 0) {
            redisService.save(PrefixConstant.VALIDATION_FAIL_IP, userLoginValidationInputDTO.getIp(), constantConfig.getIpValidateTokenFailMinutes());
            return new UserLoginValidationResultDTO(LoginValidationStatus.FAILED_OR_EXPIRED);
        }
        Token token = new Token(tokenEntries);
        return new UserLoginValidationResultDTO(token, LoginValidationStatus.SUCCESS);
    }

    /**
     * 校验登录验证失败次数是否过多
     *
     * @param ip
     * @return
     */
    private boolean isLoginValidationFailOverMaxTimes(String ip) {
        return redisService.isOverMax(PrefixConstant.VALIDATION_FAIL_IP, ip, constantConfig.getIpValidateTokenFailMaxTimes());
    }

    @Override
    public RestResult<CaptchaGenerateOutput> sendVerificationCode(CaptchaGenerateInfo captchaGenerateInfo) {
        //校验验证码发送次数是否过多
        if (isSendVerificationCodeOverMaxTimes(captchaGenerateInfo.getPhone(), captchaGenerateInfo.getIp())) {
            log.info("----send verification code => phone: {}, result: {}", captchaGenerateInfo.getPhone(), SendVerificationCodeResultMessage.SEND_VERIFICATION_CODE_FREQUENT);
            return RestResult.failure(SendVerificationCodeResultMessage.SEND_VERIFICATION_CODE_FREQUENT);
        }

        //调用基础服务，发送验证码
        RestResult<CaptchaGenerateOutput> result = RestResult.from(smsClient.sendVerifyCode(smsConfig.getAccessToken(), captchaGenerateInfo));

        //将发送记录记录到redis中
        if (result.getSuccess()) {
            redisService.save(PrefixConstant.SEND_VERIFICATION_CODE_IP, captchaGenerateInfo.getIp(), constantConfig.getVerificationCodeSendIpMinutes());
            redisService.save(PrefixConstant.SEND_VERIFICATION_CODE_PHONE, captchaGenerateInfo.getPhone(), constantConfig.getVerificationCodeSendPhoneMinutes());
        }
        log.info("----send verification code => phone: {}, result: {}", captchaGenerateInfo.getPhone(), result);
        return result;
    }

    @Override
    public List<UserDTO> findBefore(LocalDateTime dateTime) {
        return userRepository.findByCreateDateBeforeAndValidTrue(dateTime)
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RegisterLog> findSubscriptionLogsWithoutOrdersAndLeadsBefore(LocalDateTime dateTime) {
        List<RegisterLog> subscriptionLogs = registerLogRepository.findByCreateDateBeforeAndValidTrue(dateTime)
                .stream()
                .filter(subscriptionLog -> "subscription".equals(subscriptionLog.getIntention()))
                .collect(Collectors.toList());
        List<OrderDTO> orders = orderService.findBefore(dateTime);
        List<LeadsDTO> leads = leadsService.findBefore(dateTime);
        return subscriptionLogs.stream()
                .filter(subscriptionLog -> orders.stream()
                        .noneMatch(orderDTO -> Objects.equals(orderDTO.getPhone(), subscriptionLog.getPhone())))
                .filter(subscriptionLog -> leads.stream()
                        .noneMatch(leadsDTO -> Objects.equals(leadsDTO.getPhone(), subscriptionLog.getPhone())))
                .collect(Collectors.toList());
    }

    /**
     * 校验验证码发送次数是否过多
     *
     * @param phone
     * @param ip
     * @return
     */
    private boolean isSendVerificationCodeOverMaxTimes(String phone, String ip) {
        return redisService.isOverMax(PrefixConstant.SEND_VERIFICATION_CODE_IP, ip, constantConfig.getVerificationCodeSendIpMaxTimes()) ||
                redisService.isOverMax(PrefixConstant.SEND_VERIFICATION_CODE_PHONE, phone, constantConfig.getVerificationCodeSendPhoneMaxTimes());
    }
}
