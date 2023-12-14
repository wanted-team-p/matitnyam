package com.wandted.matitnyam.dto;

import lombok.Builder;

public record RestaurantDetailDto(String city, String name, String closeOrOpen, String typeOfFoods,
                                  String addressAsLocationName, String addressAsRoadName, Long numberOfReviews,
                                  Double rating) {

    @Builder
    public RestaurantDetailDto {
    }

}
