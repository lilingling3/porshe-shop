package com.boldseas.porscheshop.user.repository;

import com.boldseas.porscheshop.user.beans.RegisterLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/11.
 */
public interface RegisterLogRepository  extends JpaRepository<RegisterLog, Integer>{
    /**
     * 获取某一时点之前的留资记录
     * @param dateTime
     * @return
     */
    List<RegisterLog> findByCreateDateBeforeAndValidTrue(LocalDateTime dateTime);
}
