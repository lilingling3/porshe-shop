package com.boldseas.porscheshop.docking.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Chen Jingxuan
 * @version 2018/5/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leads_dispatch_log")
public class LeadsDispatchLog extends BaseEntity<Long> {
    /**
     * leads id
     */
    @Column(name = "leads_id")
    private Long leadsId;
    /**
     * leads_dealer id
     */
    @Column(name = "leads_dealer_id")
    private Long leadsDealerId;
    /**
     * 派发是否成功
     */
    @Column(name = "success")
    private boolean success;
    /**
     * 派发异常简要信息
     */
    @Column(name = "error_message", length = 1000)
    private String errorMessage;
}
