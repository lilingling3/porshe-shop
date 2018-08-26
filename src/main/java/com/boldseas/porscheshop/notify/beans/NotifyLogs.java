package com.boldseas.porscheshop.notify.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 通知记录
 *
 * @author feng
 */
@Entity
@Table(name = "notify_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotifyLogs extends BaseEntity<Long>{

    /**
     * 发送类型
     */
    @Column
    private String sendType;
    /**
     * 收件人
     */
    @Column
    private String sendTo;
    /**
     * 内容
     */
    @Column(length = 4000)
    private String content;
    /**
     * 发送结果
     */
    @Column(length = 4000)
    private String result;
}

