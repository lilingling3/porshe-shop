package com.boldseas.porscheshop.docking.repository;

import com.boldseas.porscheshop.docking.beans.LeadsDispatchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chen Jingxuan
 * @version 2018/5/25
 */
@Repository
public interface LeadsDispatchLogRepository extends JpaRepository<LeadsDispatchLog, Long> {
}
