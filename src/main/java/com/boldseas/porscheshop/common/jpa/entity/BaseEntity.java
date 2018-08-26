package com.boldseas.porscheshop.common.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 使用jpa的entity都应该从这个接口进行继承，定义了基本的id和创建时间和更新时间
 *
 * @author dave.wu
 * @version 2017/7/25
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime updateDate;

    /**
     * 是否有效
     */
    @Column(nullable = false, name = "valid")
    private boolean valid=true;
}