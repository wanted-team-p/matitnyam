package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import javax.persistence.Column;

/**
 * @author 민경수
 * @description region dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class RegionDto extends AbstractDto{

    @Serial
    private static final long serialVersionUID = -4045613267077937583L;


    private String city;
    private String district;
    private Float longitude;
    private Float latitude;

    public RegionDto(String city, String district, Float longitude, Float latitude) {
        this.city = city;
        this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}