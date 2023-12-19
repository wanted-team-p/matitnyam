package com.wanted.matitnyam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wanted.matitnyam.domain.Member;
import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.domain.Review;
import com.wanted.matitnyam.dto.ReviewRequest;
import com.wanted.matitnyam.exception.ResourceNotFoundException;
import com.wanted.matitnyam.exception.UnauthorizedException;
import com.wanted.matitnyam.repository.MemberRepository;
import com.wanted.matitnyam.repository.RestaurantRepository;
import com.wanted.matitnyam.repository.ReviewRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final RestaurantRepository restaurantRepository;

    public Review create(ReviewRequest reviewRequest, String username) {
        Member foundMember = findMemberByUsername(username);
        Restaurant foundRestaurant = findRestaurantById(reviewRequest.restaurantId());

        long totalRating = foundRestaurant.getTotalRatings() + reviewRequest.rating();
        long noOfReviews = foundRestaurant.getNumberOfReviews() + 1;
        Restaurant updatedRestaurant = updateRatingsOfRestaurant(foundRestaurant, totalRating, noOfReviews);

        Review review = Review.builder()
                .seq(reviewRequest.reviewId())
                .member(foundMember)
                .restaurant(updatedRestaurant)
                .rating(reviewRequest.rating())
                .opinion(reviewRequest.opinion())
                .build();
        return reviewRepository.save(review);
    }

    public Review update(ReviewRequest reviewRequest, String username) throws JsonProcessingException {
        Member foundMember = findMemberByUsername(username);
        Review foundReview = findReviewById(reviewRequest.reviewId());
        if (!foundReview.getMember().getName().equals(username)) {
            throw new UnauthorizedException("해당 리뷰를 수정할 권한이 업습니다.");
        }
        Restaurant foundRestaurant = findRestaurantById(reviewRequest.restaurantId());

        long totalRating = foundRestaurant.getTotalRatings() + reviewRequest.rating() - foundReview.getRating();
        long numberOfReviews = foundRestaurant.getNumberOfReviews();
        Restaurant updatedRestaurant = updateRatingsOfRestaurant(foundRestaurant, totalRating, numberOfReviews);

        Review review = Review.builder()
                .seq(reviewRequest.reviewId())
                .member(foundMember)
                .restaurant(updatedRestaurant)
                .rating(reviewRequest.rating())
                .opinion(reviewRequest.opinion())
                .build();
        return reviewRepository.save(review);
    }

    public void delete(Long reviewId, String username) {
        Review foundReview = findReviewById(reviewId);
        if (!foundReview.getMember().getName().equals(username)) {
            throw new UnauthorizedException("해당 리뷰를 삭제할 권한이 없습니다.");
        }
        Restaurant restaurant = findRestaurantById(foundReview.getRestaurant().getSeq());
        long totalRatings = restaurant.getTotalRatings() - foundReview.getRating();
        long numberOfReviews = restaurant.getNumberOfReviews() - 1;
        updateRatingsOfRestaurant(restaurant, totalRatings, numberOfReviews);

        reviewRepository.deleteById(reviewId);
    }

    private Member findMemberByUsername(String username) {
        Optional<Member> mayBeFoundMember = memberRepository.findByUsername(username);
        if (mayBeFoundMember.isEmpty()) {
            throw new ResourceNotFoundException("유저 정보를 찾을 수 없습니다.");
        }
        return mayBeFoundMember.get();
    }

    private Restaurant findRestaurantById(Long restaurantId) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(restaurantId);
        if (mayBeFoundRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("맛집 정보를 찾을 수 없습니다.");
        }
        return mayBeFoundRestaurant.get();
    }

    private Review findReviewById(Long reviewId) {
        Optional<Review> mayBeFoundReview = reviewRepository.findById(reviewId);
        if (mayBeFoundReview.isEmpty()) {
            throw new ResourceNotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }
        return mayBeFoundReview.get();
    }

    private Restaurant updateRatingsOfRestaurant(Restaurant restaurant, long totalRatings, long numberOfReviews) {
        restaurant.updateRatings(totalRatings, numberOfReviews);
        return restaurantRepository.save(restaurant);
    }

}
