package com.boldseas.porscheshop.pay.repository;

import com.boldseas.porscheshop.pay.bean.PaymentQrCodeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Repository
public interface PaymentQrCodeLogRepository extends JpaRepository<PaymentQrCodeLog, Long> {
    /**
     * 获取支付记录
     *
     * @param billNo
     * @return
     */
    PaymentQrCodeLog findByBillNo(String billNo);
}


