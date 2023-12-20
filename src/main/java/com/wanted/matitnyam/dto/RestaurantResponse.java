package com.wanted.matitnyam.dto;

import lombok.Builder;

public record RestaurantResponse(String name, String closeOrOpen, String typeOfFoods, String addressAsRoadName,
                                 Double rating, Double distance) {

    @Builder
    public RestaurantResponse {
    }

}
