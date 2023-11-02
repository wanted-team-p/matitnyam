package com.wandted.matitnyam.domain.entity;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
 * @description restaurant
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_restaurant")
public class Restaurant extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -5712344700397496929L;


    @Column(name = "name")
    private String name;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "out_of_business")
    private Boolean outOfBusiness;
    @Column(name = "address")
    private String address;
    @Column(name = "road_name_address")
    private String roadNameAddress;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "latitude")
    private Float latitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BusinessType> businessType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new LinkedHashSet<>();


    @Builder
    public Restaurant(String name, String licenseNumber, Boolean outOfBusiness, String address, String roadNameAddress,
                      String zipCode, Float longitude, Float latitude, Set<BusinessType> businessType) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.outOfBusiness = outOfBusiness;
        this.address = address;
        this.roadNameAddress = roadNameAddress;
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.businessType = businessType;
    }
}