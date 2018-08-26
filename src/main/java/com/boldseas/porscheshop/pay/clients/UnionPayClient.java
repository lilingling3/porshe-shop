package com.boldseas.porscheshop.pay.clients;

import com.boldseas.porscheshop.pay.dto.GateWayPayCallBackInput;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 银联支付服务
 *
 * @author huisheng.jin
 * @version 2018/5/15.
 */
@FeignClient(name = "union-pay-client", url = "${unionPay.host}", configuration = UnionPayClient.FormSupportConfig.class)
public interface UnionPayClient {
    /**
     * 表单查询支付状态
     *
     * @param entityBody 表单数据
     * @return
     */
    @RequestMapping(value = "gateway/api/queryTrans.do", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String query(Map<String, ?> entityBody);

    class FormSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }
    }
}
