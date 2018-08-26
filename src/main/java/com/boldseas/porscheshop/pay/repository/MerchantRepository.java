package com.boldseas.porscheshop.pay.repository;

import com.boldseas.porscheshop.pay.bean.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2018/5/30.
 */
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    /**
     * 获取商户
     *
     * @param dealerCode
     * @return
     */
    Merchant findByDealerCode(String dealerCode);
}


