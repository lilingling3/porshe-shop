package com.boldseas.porscheshop.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.pay.bean.PaymentUnionLog;
import com.boldseas.porscheshop.pay.clients.UnionPayClient;
import com.boldseas.porscheshop.pay.config.UnionPayConfig;
import com.boldseas.porscheshop.pay.dto.*;
import com.boldseas.porscheshop.pay.service.MerchantService;
import com.google.common.eventbus.AsyncEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * @author huisheng.jin
 * @version 2018/6/20.
 */
@Service
@Slf4j
public class UnionPayService extends BasePayService {
    private static final String UNION_PAY = "UnionPay";
    private static final String ENCODING = "UTF-8";
    public static final String LEFT_PREFIX = "{";
    public static final String RIGHT_SUFFIX = "}";
    public static final char EQUAL_CHAR = '=';

    private final OrderService orderService;
    private final MerchantService merchantService;
    private final UnionPaymentLogService unionPaymentLogService;
    private final UnionPayConfig unionPayConfig;
    private final UnionPayClient unionPayClient;

    @Autowired
    public UnionPayService(AsyncEventBus eventBus, OrderService orderService,
                           MerchantService merchantService, UnionPaymentLogService unionPaymentLogService,
                           UnionPayConfig unionPayConfig, UnionPayClient unionPayClient) {
        super(eventBus);
        this.orderService = orderService;
        this.merchantService = merchantService;
        this.unionPaymentLogService = unionPaymentLogService;
        this.unionPayConfig = unionPayConfig;
        this.unionPayClient = unionPayClient;
    }

    public String generateGateWayPayFormHtml(String orderSn, String terminal) {
        Order order = orderService.getOrderBySn(orderSn);

        MerchantDto merchant = merchantService.getMerchantId(order.getDealerCode(), PayTypeEnum.gateWay);
        String merchantOrderId = generateGateWayPayOrderId();

        GateWayPayDetail payDetail = createGateWayPayDetail(merchant.getMerchantId(), merchantOrderId, String.valueOf(order.getPrice().intValue()), orderSn);

        String formHtml = createPayFormHtml(payDetail);
        unionPaymentLogService.saveUnionPayLog(orderSn, merchantOrderId, payDetail, terminal);

        return formHtml;
    }

    private GateWayPayDetail createGateWayPayDetail(String merchantId, String merchantOrderId, String price, String orderSn) {
        return new GateWayPayDetail
                .GateWayPayDetailBuilder()
                .frontUrl(unionPayConfig.getFrontUrl() + orderSn)
                .backUrl(unionPayConfig.getBackUrl())
                .merchantId(merchantId)
                .merchantOrderId(merchantOrderId)
                .price(price)
                .build();
    }

    private String createPayFormHtml(GateWayPayDetail payDetail) {
        String requestFrontUrl = unionPayConfig.getFrontTransUrl();
        return createPayFormHtml(requestFrontUrl, payDetail.toMap());
    }

    public boolean callBack(GateWayPayCallBackInput callBackInput) {
        return syncPayResult(callBackInput);
    }

    private String generateGateWayPayOrderId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public String searchPayResult(String merchantOrderId) {
        return search(merchantOrderId);
    }

    public Boolean syncPayResult(String merchantOrderId) {
        String payResult = search(merchantOrderId);
        Map<String, String> maps = convertResultStringToMap(payResult);
        GateWayPayCallBackInput input = JSON.parseObject(JSON.toJSONString(maps), GateWayPayCallBackInput.class);

        return syncPayResult(input);
    }

    private String search(String merchantOrderId) {
        PaymentUnionLog paymentUnionLog = unionPaymentLogService.getByMerchantOrderId(merchantOrderId);
        SearchUnionPayDto searchUnionPayDto = new SearchUnionPayDto(paymentUnionLog.getMerId(), merchantOrderId);

        return unionPayClient.query(searchUnionPayDto.toMap());
    }

    private boolean syncPayResult(GateWayPayCallBackInput callBackInput) {
        PaymentUnionLog paymentUnionLog = unionPaymentLogService.getByMerchantOrderId(callBackInput.getOrderId());
        if (paymentUnionLog.isCallBacked()) {
            return true;
        }
        unionPaymentLogService.updateUnionPaymentLog(callBackInput, paymentUnionLog);

        TerminalEnum terminalEnum = TerminalEnum.valueOf(paymentUnionLog.getTerminal());
        sendMessage(callBackInput.isSuccess(), paymentUnionLog.getOrderSn(), terminalEnum, UNION_PAY);
        return true;
    }

    /**
     * 功能：前台交易构造HTTP POST自动提交表单<br>
     *
     * @param reqUrl 表单提交地址<br>
     * @param fields 以MAP形式存储的表单键值<br>
     * @return 构造好的HTTP POST交易表单<br>
     */
    private String createPayFormHtml(String reqUrl, Map<String, String> fields) {
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + ENCODING + "\"/></head><body>")
                .append("<form id = \"pay_form\" action=\"").append(reqUrl).append("\" method=\"post\">");
        if (null != fields && 0 != fields.size()) {
            Set<Map.Entry<String, String>> set = fields.entrySet();
            for (Map.Entry<String, String> ey : set) {
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"").append(key).append("\" id=\"").append(key).append("\" value=\"").append(value).append("\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }

    /**
     * 将形如key=value&key=value的字符串转换为相应的Map对象
     *
     * @param result
     * @return
     */
    private Map<String, String> convertResultStringToMap(String result) {
        Map<String, String> map = null;

        if (result != null && !"".equals(result.trim())) {
            if (result.startsWith(LEFT_PREFIX) && result.endsWith(RIGHT_SUFFIX)) {
                result = result.substring(1, result.length() - 1);
            }
            map = parseQString(result);
        }

        return map;
    }


    /**
     * 解析应答字符串，生成应答要素
     *
     * @param str 需要解析的字符串
     * @return 解析的结果map
     * @throws UnsupportedEncodingException
     */
    private Map<String, String> parseQString(String str) {

        Map<String, String> map = new HashMap<String, String>();
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        char curChar;
        String key = null;
        boolean isKey = true;
        //值里有嵌套
        boolean isOpen = false;
        char openName = 0;
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                curChar = str.charAt(i);
                if (isKey) {

                    if (curChar == EQUAL_CHAR) {
                        key = temp.toString();
                        temp.setLength(0);
                        isKey = false;
                    } else {
                        temp.append(curChar);
                    }
                } else {// 如果当前生成的是value
                    if (isOpen) {
                        if (curChar == openName) {
                            isOpen = false;
                        }

                    } else {//如果没开启嵌套
                        if (curChar == '{') {
                            isOpen = true;
                            openName = '}';
                        }
                        if (curChar == '[') {
                            isOpen = true;
                            openName = ']';
                        }
                    }

                    if (curChar == '&' && !isOpen) {
                        putKeyValueToMap(temp, isKey, key, map);
                        temp.setLength(0);
                        isKey = true;
                    } else {
                        temp.append(curChar);
                    }
                }

            }
            putKeyValueToMap(temp, isKey, key, map);
        }
        return map;
    }

    private void putKeyValueToMap(StringBuilder temp, boolean isKey,
                                  String key, Map<String, String> map) {
        if (isKey) {
            key = temp.toString();
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            map.put(key, "");
        } else {
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            map.put(key, temp.toString());
        }
    }

    /**
     * 判断字符串是否为NULL或空
     *
     * @param s 待判断的字符串数据
     * @return 判断结果 true-是 false-否
     */
    public boolean isEmpty(String s) {
        return null == s || "".equals(s.trim());
    }

}
