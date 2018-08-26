package com.boldseas.porscheshop.pay.repository;

import com.boldseas.porscheshop.pay.bean.PaymentUnionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Repository
public interface PaymentUnionLogRepository extends JpaRepository<PaymentUnionLog, Long> {
    /**
     * 获取支付记录
     *
     * @param merOrderId
     * @return
     */
    PaymentUnionLog findByOrderId(String merOrderId);
}


