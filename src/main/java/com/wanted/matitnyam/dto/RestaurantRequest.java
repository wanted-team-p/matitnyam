package com.wanted.matitnyam.dto;

import jakarta.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RestaurantRequest {

    @NotNull
    private final Double latitude;

    @NotNull
    private final Double longitude;

    @NotNull
    private final Double range;

    private final RestaurantSortType sortType;

    private final String typeOfFoods;

    @Builder
    @ConstructorProperties({"lat", "lon", "range", "sort", "food"})
    public RestaurantRequest(final Double latitude, final Double longitude, final Double range, final String sortType,
                             final String typeOfFoods) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
        if (sortType == null) {
            this.sortType = RestaurantSortType.DISTANCE;
        } else {
            this.sortType = RestaurantSortType.convertTo(sortType);
        }
        this.typeOfFoods = typeOfFoods;
    }

}
