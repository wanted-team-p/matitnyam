package com.wandted.matitnyam.dto;

import com.wandted.matitnyam.domain.Authority;
import com.wandted.matitnyam.domain.Coordinates;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PrincipalDto {

    private final String name;

    private final Authority authority;

    private Double latitude;

    private Double longitude;

    @Builder
    public PrincipalDto(String name, Authority authority) {
        this.name = name;
        this.authority = authority;
    }

    public Coordinates toCoordinates(Double latitude, Double longitude) {
        return Coordinates.builder()
                .latitudeInDegrees(latitude)
                .longitudeInDegrees(longitude)
                .build();
    }

}
