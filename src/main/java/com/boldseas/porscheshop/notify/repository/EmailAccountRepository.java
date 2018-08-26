package com.boldseas.porscheshop.notify.repository;

import com.boldseas.porscheshop.notify.beans.EmailAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author feng
 */
public interface EmailAccountRepository extends JpaRepository<EmailAccount,Long> {

    /**
     * 根据dealerCode查找email
     * @param dealerCode
     * @return
     */
    List<EmailAccount> findByDealerCodeAndValidIsTrue(String dealerCode);
}
