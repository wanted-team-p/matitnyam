package com.wanted.matitnyam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.domain.Review;
import com.wanted.matitnyam.dto.ReviewRequest;
import com.wanted.matitnyam.repository.MemberRepository;
import com.wanted.matitnyam.repository.RestaurantRepository;
import com.wanted.matitnyam.repository.ReviewRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:test/h2.sql")
@SpringBootTest
class ReviewServiceTest {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @Rollback
    @DisplayName("create 메소드 테스트: 평점 갱신 로직 확인")
    @Test
    void createTest() throws JsonProcessingException {
        Long restaurantId = 1L;
        String username = "neppiness";
        int rating = 5;
        String opinion = "최고에요!";

        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(restaurantId);
        assert mayBeFoundRestaurant.isPresent();
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();

        long numberOfReviewsBeforeUpdate = foundRestaurant.getNumberOfReviews();
        long totalRatingsBeforeUpdate = foundRestaurant.getTotalRatings();

        ReviewRequest reviewRequest = ReviewRequest.builder()
                .reviewId(null)
                .restaurantId(restaurantId)
                .rating(rating)
                .opinion(opinion)
                .build();
        Review createdReview = reviewService.create(reviewRequest, username);
        String createdReviewAsString = objectWriter.writeValueAsString(createdReview);
        System.out.println(createdReviewAsString);

        Optional<Restaurant> mayBeUpdatedRestaurant = restaurantRepository.findById(restaurantId);
        assert mayBeUpdatedRestaurant.isPresent();
        Restaurant updatedRestaurant = mayBeUpdatedRestaurant.get();

        long expectedNumberOfReviews = numberOfReviewsBeforeUpdate + 1;
        long expectedTotalRatings = totalRatingsBeforeUpdate + rating;
        double expectedRating = (double) expectedTotalRatings / expectedNumberOfReviews;

        Assertions
                .assertThat(updatedRestaurant.getNumberOfReviews())
                .isEqualTo(expectedNumberOfReviews);
        Assertions
                .assertThat(updatedRestaurant.getTotalRatings())
                .isEqualTo(expectedTotalRatings);
        Assertions
                .assertThat(updatedRestaurant.getRating())
                .isEqualTo(expectedRating);
    }

    @Rollback
    @DisplayName("update 메소드 테스트: 평점 갱신 로직 확인")
    @Test
    void updateTest() throws JsonProcessingException {
        Long restaurantId = 1L;
        String username = "neppiness";
        int originalRating = 5;
        String originalOpinion = "최고에요!";

        ReviewRequest reviewRequestToCreate = ReviewRequest.builder()
                .reviewId(null)
                .restaurantId(restaurantId)
                .rating(originalRating)
                .opinion(originalOpinion)
                .build();
        Review createdReview = reviewService.create(reviewRequestToCreate, username);
        String createdReviewAsString = objectWriter.writeValueAsString(createdReview);
        System.out.println(createdReviewAsString);

        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(restaurantId);
        assert mayBeFoundRestaurant.isPresent();
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();

        long numberOfReviewsBeforeModification = foundRestaurant.getNumberOfReviews();
        long totalRatingsBeforeModification = foundRestaurant.getTotalRatings();

        int modifiedRating = 4;
        String modifiedOpinion = "일행들은 엄청 좋아했는데 저는 음식이 조금 잘 안 맞았어요.";
        ReviewRequest reviewRequestToModify = ReviewRequest.builder()
                .reviewId(createdReview.getSeq())
                .restaurantId(restaurantId)
                .rating(modifiedRating)
                .opinion(modifiedOpinion)
                .build();
        Review modifiedReview = reviewService.update(reviewRequestToModify, username);
        String modifiedReviewAsString = objectWriter.writeValueAsString(modifiedReview);
        System.out.println(modifiedReviewAsString);

        Optional<Restaurant> mayBeModifiedRestaurant = restaurantRepository.findById(restaurantId);
        assert mayBeModifiedRestaurant.isPresent();
        Restaurant modifiedRestaurant = mayBeModifiedRestaurant.get();

        long expectedTotalRatings = totalRatingsBeforeModification - originalRating + modifiedRating;
        double expectedRating = (double) expectedTotalRatings / numberOfReviewsBeforeModification;

        Assertions
                .assertThat(modifiedRestaurant.getNumberOfReviews())
                .isEqualTo(numberOfReviewsBeforeModification);
        Assertions
                .assertThat(modifiedRestaurant.getTotalRatings())
                .isEqualTo(expectedTotalRatings);
        Assertions
                .assertThat(modifiedRestaurant.getRating())
                .isEqualTo(expectedRating);
    }

    @Rollback
    @DisplayName("delete 메소드 테스트: 평점 갱신 로직 및 데이터 변화 확인")
    @Test
    void deleteTest() throws JsonProcessingException {
        Long restaurantId = 1L;
        String username = "neppiness";
        int originalRating = 5;
        String originalOpinion = "최고에요!";

        Optional<Restaurant> mayBeFoundRestaurantBeforeCreateAndDelete = restaurantRepository.findById(restaurantId);
        assert mayBeFoundRestaurantBeforeCreateAndDelete.isPresent();
        Restaurant foundRestaurantBeforeCreateAndDelete = mayBeFoundRestaurantBeforeCreateAndDelete.get();

        long numberOfReviewsBeforeModification = foundRestaurantBeforeCreateAndDelete.getNumberOfReviews();
        long totalRatingsBeforeModification = foundRestaurantBeforeCreateAndDelete.getTotalRatings();
        double ratingBeforeModification = foundRestaurantBeforeCreateAndDelete.getRating();

        ReviewRequest reviewRequestToCreate = ReviewRequest.builder()
                .reviewId(null)
                .restaurantId(restaurantId)
                .rating(originalRating)
                .opinion(originalOpinion)
                .build();
        Review createdReview = reviewService.create(reviewRequestToCreate, username);
        String createdReviewAsString = objectWriter.writeValueAsString(createdReview);
        System.out.println(createdReviewAsString);

        reviewService.delete(createdReview.getSeq(), username);

        Optional<Restaurant> mayBeFoundRestaurantAfterCreateAndDelete = restaurantRepository.findById(restaurantId);
        assert mayBeFoundRestaurantAfterCreateAndDelete.isPresent();
        Restaurant foundRestaurantAfterCreateAndDelete = mayBeFoundRestaurantBeforeCreateAndDelete.get();

        long numberOfReviewsAfterModification = foundRestaurantAfterCreateAndDelete.getNumberOfReviews();
        long totalRatingsAfterModification = foundRestaurantAfterCreateAndDelete.getTotalRatings();
        double ratingAfterModification = foundRestaurantAfterCreateAndDelete.getRating();

        Assertions
                .assertThat(numberOfReviewsAfterModification)
                .isEqualTo(numberOfReviewsBeforeModification);
        Assertions
                .assertThat(totalRatingsAfterModification)
                .isEqualTo(totalRatingsBeforeModification);
        Assertions
                .assertThat(ratingAfterModification)
                .isEqualTo(ratingBeforeModification);

        Optional<Review> tryToFindDeletedReview = reviewRepository.findById(createdReview.getSeq());
        Assertions
                .assertThat(tryToFindDeletedReview.isEmpty())
                .isTrue();
    }

}