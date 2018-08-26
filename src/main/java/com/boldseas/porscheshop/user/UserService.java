package com.boldseas.porscheshop.user;

import com.boldseas.base.dto.CaptchaGenerateInfo;
import com.boldseas.base.dto.CaptchaGenerateOutput;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.user.beans.RegisterLog;
import com.boldseas.porscheshop.user.beans.User;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
public interface UserService {
    /**
     * 根据用户Id获取用户
     * @param id
     * @return
     */
    UserDTO findById(Long id);

    /**
     * 用户登录
     * @param userLoginInputDTO 用户登录输入参数
     * @return
     */
    UserLoginResultDTO login(UserLoginInputDTO userLoginInputDTO);

    /**
     * 用户留资
     * @param registerInputDTO
     * @return
     */
    User register(RegisterInputDTO registerInputDTO);

    /**
     * 验证用户登录状态
     * @param userLoginValidationInputDTO
     * @return
     */
    UserLoginValidationResultDTO loginValidation(UserLoginValidationInputDTO userLoginValidationInputDTO);

    /**
     * 发送验证码
     * @param captchaGenerateInfo
     * @return
     */
    RestResult<CaptchaGenerateOutput> sendVerificationCode(CaptchaGenerateInfo captchaGenerateInfo);

    /**
     * 获取某一时点之前的所有注册用户（有效用户）
     * @param dateTime
     * @return
     */
    List<UserDTO> findBefore(LocalDateTime dateTime);

    /**
     * 获取某一时点之前未下单（线上和线下订单）的订阅留资记录（有效数据）
     * @param dateTime
     * @return
     */
    List<RegisterLog> findSubscriptionLogsWithoutOrdersAndLeadsBefore(LocalDateTime dateTime);
}
