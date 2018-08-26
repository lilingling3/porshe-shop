package com.boldseas.porscheshop.user.service;

import com.boldseas.porscheshop.dtos.UserLoginLogDTO;
import com.boldseas.porscheshop.user.beans.UserLoginLog;
import com.boldseas.porscheshop.user.repository.UserLoginLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Service
public class UserLoginLogService {
    private final UserLoginLogRepository userLoginLogRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserLoginLogService(UserLoginLogRepository userLoginLogRepository, ModelMapper mapper) {
        this.userLoginLogRepository = userLoginLogRepository;
        this.mapper = mapper;
    }

    @Async
    public void saveUserLoginLog(UserLoginLogDTO userLoginLogDTO) {
        UserLoginLog userLoginLog = mapper.map(userLoginLogDTO, UserLoginLog.class);
        userLoginLogRepository.save(userLoginLog);
    }
}
