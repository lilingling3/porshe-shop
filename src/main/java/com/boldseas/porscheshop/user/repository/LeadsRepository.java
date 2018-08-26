package com.boldseas.porscheshop.user.repository;

import com.boldseas.porscheshop.user.beans.Leads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
public interface LeadsRepository extends JpaRepository<Leads, Long> {
    /**
     * 根据userId获取List
     * @param userId
     * @return
     */
    List<Leads> findByUserId(Long userId);

    /**
     * 获取某一时点之前的所有leads
     * @param dateTime
     * @return
     */
    List<Leads> findByCreateDateBefore(LocalDateTime dateTime);
}
