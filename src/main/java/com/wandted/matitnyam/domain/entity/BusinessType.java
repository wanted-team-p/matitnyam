package com.wandted.matitnyam.domain.entity;


import java.io.Serial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 민경수
 * @description business type
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_business_type")
public class BusinessType extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -945535781439389369L;

    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_seq", nullable = false)
    private Restaurant restaurant;


    @Builder
    public BusinessType(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

}