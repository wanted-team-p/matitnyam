package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description region dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionDto extends AbstractDto {

    @Serial
    private static final long serialVersionUID = -4045613267077937583L;


    private String city;
    private String district;
    private Double longitude;
    private Double latitude;

    public RegionDto(String city, String district, Double longitude, Double latitude) {
        this.city = city;
        this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}