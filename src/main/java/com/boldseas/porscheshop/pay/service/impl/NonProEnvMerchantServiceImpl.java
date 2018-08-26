package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.dto.MerchantDto;
import com.boldseas.porscheshop.pay.dto.PayTypeEnum;
import com.boldseas.porscheshop.pay.service.MerchantService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Profile("!prod")
@Service
public class NonProEnvMerchantServiceImpl implements MerchantService {

    @Override
    public MerchantDto getMerchantId(String dealerCode, PayTypeEnum payTypeEnum) {
        switch (payTypeEnum) {
            case h5QuickPass:
                return new MerchantDto("898310148160568", "88880001");
            case gateWay:
                return new MerchantDto("777290058159940", "88880001");
            default:
                return new MerchantDto("898340149000005", "88880001");
        }
    }
}
