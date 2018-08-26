package com.boldseas.porscheshop.pay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huisheng.jin
 * @version 2018/6/11.
 */
@Data
@NoArgsConstructor
public class SearchUnionPayDto extends BaseUnionPayDto {
    /**
     * 交易类型  取值：
     * 00：查询交易，01：消费，02：预授权，03：预授权完成，04：退货，05：圈存，11：代收，12：代付，
     * 13：账单支付，14：转账（保留），21：批量交易，22：批量查询，31：消费撤销，32：预授权撤销，33：预授权完成撤销，71：余额查询，
     * 72：实名认证-建立绑定关系，73：账单查询，74：解除绑定关系，75：查询绑定关系，77：发送短信验证码交易，78：开通查询交易，79：开通交易，94：IC卡脚本通知 95：查询更新加密公钥证书
     * 固定填写：01
     */
    private String txnType = "00";
    /**
     * 交易子类   依据实际交易类型填写。默认取值：00 固定填写：01：自助消费，通过地址的方式区 分前台消费和后台消费（含无跳转 支付）
     */
    private String txnSubType = "00";
    /**
     * 产品类型
     * 依据实际业务场景填写(目前仅使用后 4 位，签名 2 位 默认为 00) 默认取值：000000
     * 具体取值范围： 000201：B2C 网关支付 000301：认证支付 2.0 000302：评级支付 000401：代付 000501：代收 000601：账单支付 000801：跨行收单 000901：绑定支付 001001：订购 000202：B2B 000201
     */
    private String bizType = "000000";

    public SearchUnionPayDto(String merId, String orderId) {
        super.setMerId(merId);
        super.setOrderId(orderId);

        createSign();
    }
}
