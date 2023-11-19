package com.wandted.matitnyam.restaurant.dto;

public record RestaurantListResponse(
        Long id,
        String name,
        Double longitude,
        Double latitude
) {
}
