package com.wanted.matitnyam.dto;

import lombok.Builder;

public record RestaurantDetailResponse(String city, String name, String closeOrOpen, String typeOfFoods,
                                       String addressAsLocationName, String addressAsRoadName, Long numberOfReviews,
                                       Double rating) {

    @Builder
    public RestaurantDetailResponse {
    }

}
