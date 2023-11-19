package com.wandted.matitnyam.restaurant.dto;

public record RestaurantRequestParam(
        long lat,
        long lon,
        long range,
        String orderBy,
        String orderDirection
) {
}
