package com.wandted.matitnyam.domain.dto;

import java.io.Serial;

/**
 * @author 민경수
 * @description restaurant dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class RestaurantDto extends AbstractDto{
    @Serial
    private static final long serialVersionUID = -6779438261564881370L;


    private String name;
    private String licenseNumber;
    private Boolean outOfBusiness;
    private String address;
    private String roadNameAddress;
    private String zipCode;
    private Float longitude;
    private Float latitude;

    public RestaurantDto(String name, String licenseNumber, Boolean outOfBusiness, String address,
                         String roadNameAddress,
                         String zipCode, Float longitude, Float latitude) {
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