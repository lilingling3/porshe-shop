package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.bean.Merchant;
import com.boldseas.porscheshop.pay.dto.MerchantDto;
import com.boldseas.porscheshop.pay.dto.PayTypeEnum;
import com.boldseas.porscheshop.pay.repository.MerchantRepository;
import com.boldseas.porscheshop.pay.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Profile("prod")
@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public MerchantDto getMerchantId(String dealerCode, PayTypeEnum payTypeEnum) {
        Merchant merchant = merchantRepository.findByDealerCode(dealerCode);
        switch (payTypeEnum) {
            case qrCode:
                return new MerchantDto(merchant.getQrCodeNo(), merchant.getQrCodeTerminalNo());
            case h5AliPay:
                return new MerchantDto(merchant.getH5No(), merchant.getH5TerminalNo());
            case h5QuickPass:
                return new MerchantDto(merchant.getH5No(), merchant.getH5TerminalNo());
            case gateWay:
                return new MerchantDto(merchant.getGateWayNo(), merchant.getGateWayTerminalNo());
            case wxPublic:
                return new MerchantDto(merchant.getWxPublicNo(), merchant.getWxPublicTerminalNo());
            default:
                throw new IllegalArgumentException(String.format("--- getMerchantId error,dealerCode:%s,payType:%s", dealerCode, payTypeEnum.toString()));
        }
    }
}
