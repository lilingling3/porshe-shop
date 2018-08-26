package com.boldseas.porscheshop.notify.service;

import com.alibaba.fastjson.JSON;
import com.boldseas.base.client.EmailClient;
import com.boldseas.base.client.SmsClient;
import com.boldseas.base.dto.EmailResponse;
import com.boldseas.base.dto.TemplateEmailModel;
import com.boldseas.base.dto.TemplateSmsSendModel;
import com.boldseas.base.dto.UploadResponse;
import com.boldseas.porscheshop.EventListener;
import com.boldseas.porscheshop.client.BaseDataClient;
import com.boldseas.porscheshop.common.config.EmailConfig;
import com.boldseas.porscheshop.common.config.SmsConfig;
import com.boldseas.porscheshop.common.utils.FileToMultipartFileUtils;
import com.boldseas.porscheshop.common.utils.Md5Util;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.common.utils.ExcelHelper;
import com.boldseas.porscheshop.notify.NotifyService;
import com.boldseas.porscheshop.notify.beans.EmailAccount;
import com.boldseas.porscheshop.notify.beans.NotifyLogs;
import com.boldseas.porscheshop.notify.repository.EmailAccountRepository;
import com.boldseas.porscheshop.notify.repository.NotifyRepository;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.user.UserService;
import com.google.common.eventbus.Subscribe;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.boldseas.porscheshop.common.constant.CommonConstants.DATE_FORMATTER_WORD;
import static com.boldseas.porscheshop.common.constant.CommonConstants.MD5_SIGN;

/**
 * 短信邮件通知
 *
 * @author feng
 */
@Slf4j
@Service
@EventListener
public class NotifyServiceImpl implements NotifyService {
    private final String DAILY_REPORT_EXCEL_NAME = String.format("全新 Taycan 意向金计划数据 截止 %s .xlsx", getCurrentDate(-1L));
    private final String PAYED_ORDERS_EXCEL_NAME = String.format("截至 %s Taycan 线上支付意向金名单.xlsx", getCurrentDate(-1L));

    @Value("${pmp.apiKey}")
    private String apiKey;
    @Value("${server.host}")
    private String host;

    private SmsClient smsClient;
    private SmsConfig smsConfig;
    private EmailClient emailClient;
    private EmailConfig emailConfig;
    private OrderService orderService;
    private NotifyRepository notifyRepository;
    private ModelMapper mapper;
    private UserService userService;
    private EmailAccountRepository emailAccountRepository;

    @Autowired
    public NotifyServiceImpl(SmsClient smsClient, SmsConfig smsConfig, EmailClient emailClient, EmailConfig emailConfig, OrderService orderService, NotifyRepository notifyRepository, ModelMapper mapper, UserService userService, EmailAccountRepository emailAccountRepository) {
        this.smsClient = smsClient;
        this.smsConfig = smsConfig;
        this.emailClient = emailClient;
        this.emailConfig = emailConfig;
        this.orderService = orderService;
        this.notifyRepository = notifyRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.emailAccountRepository = emailAccountRepository;
    }
    /**
     * 发送订单支付成功短信
     * @param order
     */
    @Override
    public void sendPaySuccessSMS(OrderDTO order) {
        UserDTO userDTO = userService.findById(order.getUserId());

        Map<String, Object> map = new HashMap<>(4);
        map.put("user", order.getConsignee());
        map.put("order", order.getOrderSn());
        map.put("name", userDTO.getLastName() + userDTO.getFirstName());
        map.put("gender", userDTO.getGenderValue());
        String phone = order.getPhone();
        smsClient.sendTemplate(smsConfig.getPaySuccessToken(), new TemplateSmsSendModel(phone, map));
    }
    /**
     * 给经销商发送用户申请退款邮件
     * @param order
     */
    @Override
    public void sendRefundMail(OrderDTO order) {
        log.info("sendRefundMail:申请退款邮件，order:\n {}",order);
        UserDTO userDTO = userService.findById(order.getUserId());

        setOrderStatusTime(order);
        String token = emailConfig.getRefundToken();
        Map<String, Object> map = new HashMap<>(6);
        map.put("order", order);
        map.put("name", userDTO.getLastName() + userDTO.getFirstName());
        map.put("gender", userDTO.getGenderValue());
        map.put("date",getCurrentDate());
        map.put("createDate", order.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        map.put("paymentDate", order.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        TemplateEmailModel template = new TemplateEmailModel();
        template.setSendTo(getEmailByDealerCode(order.getDealerCode()));
        template.setTemplateParams(map);
        EmailResponse result = emailClient.sendEmailUseTemplate(token, template);
        notifyRepository.save(new NotifyLogs("sendRefundMail", getEmailByDealerCode(order.getDealerCode()),JSON.toJSONString(map),result.getResponseMessage()));
    }

    /**
     * 设置订单创建和支付时间
     * @param orderDTO
     */
    private void setOrderStatusTime(OrderDTO orderDTO) {
        List<OrderStatusDTO> statusDTOS = orderService.getOrderStatusBySn(orderDTO.getOrderSn());
        List<OrderStatusDTO> waiting = statusDTOS.stream().filter(o -> o.getStatus().equals(OrderStatusEnum.waitingPay)).collect(Collectors.toList());
        List<OrderStatusDTO> payment = statusDTOS.stream().filter(o -> o.getStatus().equals(OrderStatusEnum.paySuccess)).collect(Collectors.toList());
        if (waiting.size() > 0) {
            orderDTO.setCreateDate(waiting.get(0).getCreateDate());
        }
        if (payment.size() > 0) {
            orderDTO.setPaymentTime(payment.get(0).getCreateDate());
        }

    }

    /**
     * 给经销商发送前一天支付成功的订单邮件
     */
    @Override
    public void sendYesterdayPaySuccessMail() {
        XxlJobLogger.log("sendYesterdayPaySuccessMail:\n\n给经销商发送截止前一天支付成功的订单邮件开始");
        List<OrderDTO> orders = orderService.getPaySuccessOrdersBeForeToday();
        Map<String, List<OrderDTO>> dealerOrdersMap = orders.stream().collect(Collectors.groupingBy(OrderDTO::getDealerCode));
        XxlJobLogger.log("\n\n邮件总数：{0}",dealerOrdersMap.size());
        dealerOrdersMap.forEach(this::sendPaySuccessMail);
        XxlJobLogger.log("\n\n发送完成！\n\n");
    }

    @Override
    public void sendDailyReportMail() {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        XxlJobLogger.log("sendDailyReportMail: 发送保时捷商城日报邮件开始");
        //获取日报数据
        DailyReportDTO dailyReportDTO = new DailyReportDTO();
        dailyReportDTO.setUserNumber(userService.findBefore(startOfToday));
        dailyReportDTO.setSubscriptionData(userService.findSubscriptionLogsWithoutOrdersAndLeadsBefore(startOfToday));
        dailyReportDTO.setOrderData(orderService.findBefore(startOfToday));

        //生成日报excel文件流
        ByteArrayOutputStream excelStream;
        try {
            excelStream = ExcelHelper.getOutStream(dailyReportDTO.convertTo());
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            XxlJobLogger.log("\nsendDailyReportMail: 生成日报Excel异常！\n {0}", e);
            return;
        }

        //发送日报邮件
        TemplateEmailModel template = new TemplateEmailModel();
        template.setSendTo(emailConfig.getDailyReportAddresses());
        template.setAttachmentNames(getAttachment(excelStream, DAILY_REPORT_EXCEL_NAME));
        EmailResponse result = emailClient.sendEmailUseTemplate(emailConfig.getDailyReportToken(), template);

        XxlJobLogger.log("\nsendDailyReportMail: 发送完成！发送结果：" + result.getResponseMessage());
    }

    /**
     * 提交到店预订发送短信
     * @param leadsDTO
     */
    @Subscribe
    public void sendOffLinePaymentSms(LeadsDTO leadsDTO){
        Map<String, Object> map = new HashMap<>(1);
        map.put("leads",leadsDTO);
        String phone = leadsDTO.getPhone();
        smsClient.sendTemplate(smsConfig.getOffLinePaymentToken(), new TemplateSmsSendModel(phone, map));
    }

    /**
     * 给经销商发送邮件
     * @param dealerCode
     * @param orderDTOS
     */
    private void sendPaySuccessMail(String dealerCode, List<OrderDTO> orderDTOS) {
        String token = emailConfig.getPaySuccessToken();
        TemplateEmailModel template = new TemplateEmailModel();
        try {
            template.setSendTo(getEmailByDealerCode(dealerCode));
            template.setTemplateParams(getPaySuccessMailMap(orderDTOS));
            template.setAttachmentNames(getAttachment(orderDTOS));
        } catch (Exception e) {
            XxlJobLogger.log("\n发送邮件附件出错\n Data: {0}\nException: \n{1}", orderDTOS, e);
            notifyRepository.save(new NotifyLogs("sendPaySuccessMail", dealerCode, JSON.toJSONString(orderDTOS), JSON.toJSONString(e)));
            return;
        }
        EmailResponse result = emailClient.sendEmailUseTemplate(token, template);
        XxlJobLogger.log("\n\n经销商Code：{0} ,已发送。\n\n数据：{1}\n\n结果：{2}", dealerCode, JSON.toJSONString(orderDTOS), result);
        notifyRepository.save(new NotifyLogs("sendPaySuccessMail", getEmailByDealerCode(dealerCode), JSON.toJSONString(orderDTOS), result.getResponseMessage()));
    }

    private String getEmailByDealerCode(String dealerCode) {
        List<EmailAccount> emailAccounts = emailAccountRepository.findByDealerCodeAndValidIsTrue(dealerCode);
        if (emailAccounts.size() < 1) {
            throw new IllegalArgumentException("dealerCode : " + dealerCode + " not found email address!");
        }
        List<String> emails = emailAccounts.stream().map(EmailAccount::getEmail).collect(Collectors.toList());
        return String.join(",", emails);
    }

    /**
     * 设置邮件中的模板数据源
     */
    private Map<String, Object> getPaySuccessMailMap(List<OrderDTO> orderDTOS){
        Map<String, Object> map = new HashMap<>(3);
        map.put("dealer", orderDTOS.get(0).getDealerName());
        map.put("date",getCurrentDate(-1L));
        map.put("orderList",orderDTOS);
        return map;
    }

    /**
     * 订单邮件设置邮件中携带的附件
     */
    private Set<String> getAttachment(List<OrderDTO> orderDTOS) throws Exception {
        List<Object> excelData = getExcelData(orderDTOS);
        OutputStream excelStream = getExcelStream(excelData, emailConfig.getExcelPassword());
        UploadResponse upload = uploadFile(excelStream, PAYED_ORDERS_EXCEL_NAME);
        Set<String> attachmentNames = new HashSet<>();
        attachmentNames.add(upload.getAttachmentName());
        XxlJobLogger.log("\n上传附件结果：" + upload.getAttachmentName());
        return attachmentNames;
    }

    /**
     * 获取邮件中携带的附件（先上传附件）
     * @param fileStream 文件流
     * @param fileName 文件名
     * @return
     */
    private Set<String> getAttachment(OutputStream fileStream, String fileName) {
        UploadResponse upload = uploadFile(fileStream, fileName);
        Set<String> attachmentNames = new HashSet<>();
        attachmentNames.add(upload.getAttachmentName());
        XxlJobLogger.log("\n上传附件结果：" + upload.getAttachmentName());
        return attachmentNames;
    }

    /**
     * 生成Excel并上传附件
     * @param outputStream
     * @return
     */
    private UploadResponse uploadFile(OutputStream outputStream, String fileName) {
        MultipartFile attachments = new FileToMultipartFileUtils(fileName, outputStream);
        return emailClient.uploadAttachment(attachments);
    }

    /**
     * 获取Excel文件流
     * @param data
     * @param password
     * @return
     */
    private OutputStream getExcelStream(List<Object> data,String password) throws Exception {
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getOutStreamEncrypt(data,password);
    }

    /**
     * 组装附件Excel中的数据源
     * @param orderDTOS
     * @return
     */
    private List<Object> getExcelData(List<OrderDTO> orderDTOS) {
        List<Object> data = orderDTOS.stream()
                .map(orderDTO -> {
                    setOrderStatusTime(orderDTO);
                    UserDTO userDTO = userService.findById(orderDTO.getUserId());
                    orderDTO.setUserInfo(userDTO);
                    OrderMailDTO orderMailDTO = mapper.map(userDTO, OrderMailDTO.class);
                    mapper.map(orderDTO, orderMailDTO);
                    orderMailDTO.setOrderStatus(orderDTO.getStatus().getDesc());
                    orderMailDTO.setCity(userDTO.getCity());
                    orderMailDTO.setProvince(userDTO.getProvince());
                    orderMailDTO.setCreateDate(orderDTO.getCreateDate().toString());
                    orderMailDTO.setDownloadPdf(host + "account/download/" + orderDTO.getOrderSn() + "/" + getSign(orderDTO.getOrderSn()));
                    if (Objects.nonNull(orderDTO.getPaymentTime())) {
                        orderMailDTO.setPaymentTime(orderDTO.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    }
                    return orderMailDTO;
                }).sorted(Comparator.comparing(OrderMailDTO::getPaymentTime).reversed())
                .collect(Collectors.toList());
        return data;
    }

    /**
     * 获取当前日期
     * @return
     */
    private String getCurrentDate() {
        return getCurrentDate(0L);
    }
    private String getCurrentDate(Long plusDay){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER_WORD);
        return LocalDate.now().plusDays(plusDay).format(formatter);
    }
    /**
     * 获取签名
     * @param str
     * @return
     */
    private String getSign(String str){
        return Md5Util.generateMd5(str,MD5_SIGN);
    }
}
