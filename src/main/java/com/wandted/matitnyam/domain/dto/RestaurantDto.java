package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description restaurant dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantDto extends AbstractDto {
    @Serial
    private static final long serialVersionUID = -6779438261564881370L;


    private String name;
    private String licenseNumber;
    private Boolean outOfBusiness;
    private String address;
    private String roadNameAddress;
    private String zipCode;
    private Double longitude;
    private Double latitude;

    public RestaurantDto(String name, String licenseNumber, Boolean outOfBusiness, String address,
                         String roadNameAddress,
                         String zipCode, Double longitude, Double latitude) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.outOfBusiness = outOfBusiness;
        this.address = address;
        this.roadNameAddress = roadNameAddress;
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}