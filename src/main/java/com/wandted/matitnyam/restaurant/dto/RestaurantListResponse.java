package com.wandted.matitnyam.restaurant.dto;

public record RestaurantListResponse(
        Long id,
        String name,
        Long longitude,
        Long latitude
) {
}
