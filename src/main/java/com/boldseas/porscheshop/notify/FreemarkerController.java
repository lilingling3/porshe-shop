package com.boldseas.porscheshop.notify;

import com.boldseas.porscheshop.dtos.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预览通知模板数据绑定
 * @author feng
 */
@Controller
@RequestMapping("/template")
public class FreemarkerController {

    /**
     * 支付成功
     * @param modelMap
     * @return
     */
    @GetMapping({"sms/paysuccess"})
    public String sendPaysuccessSms(ModelMap modelMap){
        modelMap.put("user", "张三");
        modelMap.put("order", "FASDFASDF");
        return "notitytemplate/paysuccess";
    }

    /**
     * 到店预订
     * @param modelMap
     * @return
     */
    @GetMapping({"sms/offlinepayment"})
    public String sendOffLinePaymentSms(ModelMap modelMap){
        modelMap.addAllAttributes(getOffLinePaymentSmsMap());
        return "notitytemplate/offlinepayment";
    }

    /**
     * 申请退款
     * @param modelMap
     * @return
     */
    @GetMapping({"mail/refund"})
    public String sendRefundOrderMail(ModelMap modelMap){
        modelMap.addAllAttributes(getRefundMap());
        return "notitytemplate/refund";
    }

    /**
     * 定时邮件
     * @param modelMap
     * @return
     */
    @GetMapping({"mail/timer"})
    public String sendPaysuccessTimer(ModelMap modelMap) {
        modelMap.addAllAttributes(getPaySuccessMap());
        return "notitytemplate/timer";
    }

    private Map<String, Object> getPaySuccessMap() {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(getNewOrderDto());
        orderDTOS.add(getNewOrderDto());
        orderDTOS.add(getNewOrderDto());
        orderDTOS.add(getNewOrderDto());
        orderDTOS.add(getNewOrderDto());
        orderDTOS.add(getNewOrderDto());

        Map<String, Object> map = new HashMap<>(3);
        map.put("dealer", "北京中关村保时捷中心");
        map.put("date", getCurrentDate());
        map.put("orderList", orderDTOS);
        return map;
    }

    private Map<String, Object> getRefundMap() {
        OrderDTO order = getNewOrderDto();

        Map<String, Object> map = new HashMap<>(4);
        map.put("order", order);
        map.put("date", getCurrentDate());
        map.put("createDate", order.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        map.put("paymentDate", order.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));

        return map;
    }

    private OrderDTO getNewOrderDto(){
        OrderDTO order =new OrderDTO();
        order.setConsignee("张三");
        order.setPhone("18632123441");
        order.setDealerName("北京中关村保时捷中心");
        order.setOrderSn("ADSFASDFASDFASDFASDFASDF");
        order.setIdCard("523425234523452345345");
        order.setCreateDate(LocalDateTime.now().minusMinutes(5));
        order.setPaymentTime(LocalDateTime.now());
        return order;
    }

    private Map<String,Object> getOffLinePaymentSmsMap(){
        LeadsDTO leadsDTO =new LeadsDTO();
        leadsDTO.setFirstName("三哥");
        leadsDTO.setLastName("张");
        leadsDTO.setGender(Gender.MALE);
        leadsDTO.setPhone("186123123123");

        LeadsDealerDTO leadsDealerDTO1=new LeadsDealerDTO();
        leadsDealerDTO1.setDealerName("北京中关村保时捷中心");
        LeadsDealerDTO leadsDealerDTO2=new LeadsDealerDTO();
        leadsDealerDTO2.setDealerName("北京上地保时捷中心");
        LeadsDealerDTO leadsDealerDTO3=new LeadsDealerDTO();
        leadsDealerDTO3.setDealerName("北京西二旗保时捷中心");

        List<LeadsDealerDTO> leadsDealerDTOs=new ArrayList<>();
        leadsDealerDTOs.add(leadsDealerDTO1);
        leadsDealerDTOs.add(leadsDealerDTO2);
        leadsDealerDTOs.add(leadsDealerDTO3);

        leadsDTO.setLeadsDealers(leadsDealerDTOs);

        Map<String, Object> map = new HashMap();
        map.put("leads",leadsDTO);
        return map;
    }

    /**
     * 获取当前日期
     * @return
     */
    private String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        return LocalDate.now().format(formatter);
    }
}
