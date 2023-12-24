package com.wanted.matitnyam.dto;

import lombok.Builder;

public record ReviewResponse(Long seq, MemberResponse memberResponse, RestaurantDetailResponse restaurantDetailResponse,
                             Integer rating, String opinion) {

    @Builder
    public ReviewResponse {
    }

}
