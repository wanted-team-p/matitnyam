package com.wandted.matitnyam.dto;

import lombok.Builder;

public record RestaurantDto(String name, String closeOrOpen, String typeOfFoods, String addressAsRoadName,
                            Double rating, Double distance) {

    @Builder
    public RestaurantDto {
    }

}
