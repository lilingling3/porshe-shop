package com.boldseas.porscheshop.ui.api;

import com.boldseas.base.dto.CaptchaGenerateInfo;
import com.boldseas.base.dto.CaptchaGenerateOutput;
import com.boldseas.porscheshop.common.config.ConstantConfig;
import com.boldseas.porscheshop.common.config.CookieConfig;
import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.common.utils.CookieUtil;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.user.RedisService;
import com.boldseas.porscheshop.user.UserService;
import com.boldseas.porscheshop.user.beans.User;
import com.boldseas.porscheshop.common.utils.RequestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@RestController
@RequestMapping("/v1/api")
public class UserApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ConstantConfig constantConfig;
    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisService redisService;

    /**
     * 保存用户留资信息
     *
     * @param registerInputDTO
     * @return
     */
    @PostMapping(path = "user/register")
    public RegisterResultDTO register(@RequestBody @Valid RegisterInputDTO registerInputDTO) {
        Long userId = userProvider.getCurrentUser().getId();
        registerInputDTO.setId(userId);
        User user = userService.register(registerInputDTO);
        boolean existValidOrder = orderService.isExistValidOrder(userId);
        return new RegisterResultDTO(true, existValidOrder, mapper.map(user, UserDTO.class));
    }

    /**
     * 用户登录接口
     *
     * @param userLoginInputDTO
     * @param request
     * @return
     */
    @PostMapping(path = "user/login")
    public RestResult<UserLoginResultDTO> login(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestBody @Valid UserLoginInputDTO userLoginInputDTO) throws UnsupportedEncodingException {
        userLoginInputDTO.setIp(RequestUtils.getUserIp(request));
        userLoginInputDTO.setDevice(DeviceUtils.getCurrentDevice(request));
        UserLoginResultDTO userLoginResult = userService.login(userLoginInputDTO);
        if (userLoginResult.isSuccess()) {
            CookieUtil.addCookie(request, response, CommonConstants.AUTH_TOKEN_COOKIE_NAME, userLoginResult.getAuthToken(), constantConfig.getIntLoginExpireTime(), null, cookieConfig.getDomain());
        }
        return new RestResult<>(userLoginResult.isSuccess(), userLoginResult.getMessage(), userLoginResult);
    }

    /**
     * 验证用户登录状态
     *
     * @param userLoginValidationInputDTO
     * @param request
     * @return
     */
    @PostMapping(path = "user/login/validate")
    public RestResult<UserLoginValidationResultDTO> loginValidation(HttpServletRequest request,
                                                                    @RequestBody @Valid UserLoginValidationInputDTO userLoginValidationInputDTO) {
        userLoginValidationInputDTO.setIp(RequestUtils.getUserIp(request));
        UserLoginValidationResultDTO validationResultDTO = userService.loginValidation(userLoginValidationInputDTO);
        return new RestResult<>(validationResultDTO.isSuccess(), validationResultDTO.getMessage(), validationResultDTO);
    }

    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeInput
     * @param request
     * @return
     */
    @PostMapping(path = "user/send_verification_code")
    public RestResult<CaptchaGenerateOutput> sendVerificationCode(@RequestBody @Valid SendVerificationCodeInputDTO sendVerificationCodeInput,
                                                                  HttpServletRequest request) {
        sendVerificationCodeInput.setIp(RequestUtils.getUserIp(request));
        CaptchaGenerateInfo captchaGenerateInfo = mapper.map(sendVerificationCodeInput, CaptchaGenerateInfo.class);
        return userService.sendVerificationCode(captchaGenerateInfo);
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping(path = "user/logout")
    public RestResult logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String authToken = CookieUtil.getCookie(request, CommonConstants.AUTH_TOKEN_COOKIE_NAME);
        if (!StringUtils.isEmpty(authToken)) {
            CookieUtil.removeCookie(request, response, CommonConstants.AUTH_TOKEN_COOKIE_NAME, null, cookieConfig.getDomain());
            redisService.deleteKey(PrefixConstant.LOGIN_AUTH_TOKEN, authToken);
        }
        return RestResult.success();
    }
}
