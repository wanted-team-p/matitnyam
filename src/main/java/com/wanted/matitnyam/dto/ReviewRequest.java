package com.wanted.matitnyam.dto;

import jakarta.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import lombok.Builder;

public record ReviewRequest(Long reviewId, @NotNull Long restaurantId, @NotNull Integer rating, String opinion) {

    @Builder
    @ConstructorProperties({"id", "restaurant-id", "rating", "opinion"})
    public ReviewRequest(final Long reviewId, final Long restaurantId, final Integer rating, final String opinion) {
        this.reviewId = reviewId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.opinion = opinion;
    }

    public static ReviewRequest createReviewRequestByReviewIdAndRequest(final Long reviewId,
                                                                        final ReviewRequest reviewRequest) {
        return ReviewRequest.builder()
                .reviewId(reviewId)
                .restaurantId(reviewRequest.restaurantId)
                .rating(reviewRequest.rating)
                .opinion(reviewRequest.opinion)
                .build();
    }

}
