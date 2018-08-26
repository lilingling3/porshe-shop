package com.boldseas.porscheshop.notify.repository;

import com.boldseas.porscheshop.notify.beans.NotifyLogs;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yefei
 */
public interface NotifyRepository extends JpaRepository<NotifyLogs,Long> {
}
