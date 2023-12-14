package com.wandted.matitnyam.dto;

import jakarta.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewRequest {

    private final Long reviewId;

    @NotNull
    private final Long restaurantId;

    @NotNull
    private final Integer rating;

    private final String opinion;

    @Builder
    @ConstructorProperties({"id", "restaurant-id", "rating", "opinion"})
    public ReviewRequest(final Long reviewId, final Long restaurantId, final Integer rating, final String opinion) {
        this.reviewId = reviewId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.opinion = opinion;
    }

    public static ReviewRequest createReviewRequestByReviewIdAndRequest(final Long reviewId, final ReviewRequest reviewRequest) {
        return ReviewRequest.builder()
                .reviewId(reviewId)
                .restaurantId(reviewRequest.restaurantId)
                .rating(reviewRequest.rating)
                .opinion(reviewRequest.opinion)
                .build();
    }

}
