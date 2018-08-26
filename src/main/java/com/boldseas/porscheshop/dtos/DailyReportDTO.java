package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.utils.ExcelColumnName;
import com.boldseas.porscheshop.user.beans.RegisterLog;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Chen Jingxuan
 * @version 2018/7/11
 */
@Data
public class DailyReportDTO {
    /**
     * 用户数
     */
    @ExcelColumnName("Log-in(Register)")
    private Integer userNumber;
    /**
     * 订阅留资且无订单的留资数
     */
    @ExcelColumnName("仅订阅资讯人数")
    private Integer subscriptionNumber;
    /**
     * 订阅留资无订单且有email信息的留资数
     */
    @ExcelColumnName("仅订阅资讯人数中填了e-mail的人数")
    private Integer subscriptionWithEmailNumber;
    /**
     * 订单总数
     */
    @ExcelColumnName("在线支付订单量")
    private Integer totalOrderNumber;
    /**
     * 已支付订单数
     */
    @ExcelColumnName("- 已支付")
    private Integer payedOrderNumber;
    /**
     * 未支付订单数
     */
    @ExcelColumnName("- 未支付")
    private Integer unPayedOrderNumber;
    /**
     * 已删除订单数
     */
    @ExcelColumnName("- 已删除")
    private Integer deletedOrderNumber;
    /**
     * 退款申请中订单数
     */
    @ExcelColumnName("- 退款申请中")
    private Integer pendingRefundOrderNumber;
    /**
     * 退款成功订单数
     */
    @ExcelColumnName("- 退款成功")
    private Integer refundedOrderNumber;



    public void setUserNumber(List<UserDTO> users) {
        this.userNumber = users.size();
    }
    
    public void setSubscriptionData(List<RegisterLog> subscriptionLogs) {
        this.subscriptionNumber = (int)subscriptionLogs.stream()
                .map(RegisterLog::getPhone)
                .distinct().count();
        this.subscriptionWithEmailNumber = (int)subscriptionLogs.stream()
                .filter(subscriptionLog -> !StringUtils.isEmpty(subscriptionLog.getEmail()))
                .map(RegisterLog::getPhone)
                .distinct().count();
    }
    
    public void setOrderData(List<OrderDTO> orders) {
        this.totalOrderNumber = orders.size();
        this.payedOrderNumber = (int)orders.stream().filter(order -> Objects.equals(order.getStatus(), OrderStatusEnum.paySuccess)).count();
        this.unPayedOrderNumber = (int)orders.stream().filter(order -> Objects.equals(order.getStatus(), OrderStatusEnum.waitingPay)).count();
        this.deletedOrderNumber = (int)orders.stream().filter(order -> Objects.equals(order.getStatus(), OrderStatusEnum.deleted)).count();
        this.pendingRefundOrderNumber = (int)orders.stream().filter(order -> Objects.equals(order.getStatus(), OrderStatusEnum.refund)).count();
        this.refundedOrderNumber = (int)orders.stream().filter(order -> Objects.equals(order.getStatus(), OrderStatusEnum.refundSuccess)).count();
    }

    public String getUserNumber() {
        return Objects.nonNull(userNumber) ? userNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getSubscriptionNumber() {
        return Objects.nonNull(subscriptionNumber) ? subscriptionNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getSubscriptionWithEmailNumber() {
        return Objects.nonNull(subscriptionWithEmailNumber) ? subscriptionWithEmailNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getTotalOrderNumber() {
        return Objects.nonNull(totalOrderNumber) ? totalOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getPayedOrderNumber() {
        return Objects.nonNull(payedOrderNumber) ? payedOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getUnPayedOrderNumber() {
        return Objects.nonNull(unPayedOrderNumber) ? unPayedOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getDeletedOrderNumber() {
        return Objects.nonNull(deletedOrderNumber) ? deletedOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getPendingRefundOrderNumber() {
        return Objects.nonNull(pendingRefundOrderNumber) ? pendingRefundOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public String getRefundedOrderNumber() {
        return Objects.nonNull(refundedOrderNumber) ? refundedOrderNumber.toString() : CommonConstants.EMPTY_STRING;
    }

    public List<Object> convertTo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> dailyReportExcelDTOList = new ArrayList<>();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean fieldHasAnnotation = field.isAnnotationPresent(ExcelColumnName.class);
            if (fieldHasAnnotation) {
                ExcelColumnName excelColumnNameAnnotation = field.getAnnotation(ExcelColumnName.class);

                String name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = this.getClass().getMethod("get" + name);
                String value = (String) m.invoke(this);

                DailyReportExcelDTO dailyReportExcelDTO = new DailyReportExcelDTO(excelColumnNameAnnotation.value(), value);
                dailyReportExcelDTOList.add(dailyReportExcelDTO);
            }

        }

        return dailyReportExcelDTOList;
    }

}
