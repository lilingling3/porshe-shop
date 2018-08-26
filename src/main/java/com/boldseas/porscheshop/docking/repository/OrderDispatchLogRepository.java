package com.boldseas.porscheshop.docking.repository;

import com.boldseas.porscheshop.docking.beans.OrderDispatchLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chen Jingxuan
 * @version 2018/5/25
 */
public interface OrderDispatchLogRepository extends JpaRepository<OrderDispatchLog, Long> {
}
