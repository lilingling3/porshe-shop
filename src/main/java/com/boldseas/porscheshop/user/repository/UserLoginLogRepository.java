package com.boldseas.porscheshop.user.repository;

import com.boldseas.porscheshop.user.beans.UserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
public interface UserLoginLogRepository extends JpaRepository<UserLoginLog, Long> {
}
