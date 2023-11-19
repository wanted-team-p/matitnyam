package com.wandted.matitnyam.restaurant.dto;

import com.wandted.matitnyam.region.domain.District;

public record RestaurantDetailResponse(
    Long id,
    String name,
    District district,
    String address,
    Double latitude,
    Double longitude
) {
}
