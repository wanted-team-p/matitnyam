package com.wandted.matitnyam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CoordinatesRequest {

    @NotNull
    Double latitude;

    @NotNull
    Double longitude;

    @Builder
    public CoordinatesRequest(final Double latitude, final Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
