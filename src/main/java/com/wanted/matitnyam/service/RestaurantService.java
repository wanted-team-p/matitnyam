package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Coordinates;
import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.domain.Review;
import com.wanted.matitnyam.dto.Mobility;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.RegionRequest;
import com.wanted.matitnyam.dto.RestaurantDetailResponse;
import com.wanted.matitnyam.dto.RestaurantResponse;
import com.wanted.matitnyam.dto.RestaurantRequest;
import com.wanted.matitnyam.dto.ReviewShortResponse;
import com.wanted.matitnyam.exception.ResourceNotFoundException;
import com.wanted.matitnyam.repository.RestaurantRepository;
import com.wanted.matitnyam.util.DosiSggFinder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final DosiSggFinder dosiSggFinder;

    public Restaurant upload(Restaurant restaurant) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findByNameAndAddressAsRoadName(
                restaurant.getName(), restaurant.getAddressAsRoadName());
        if (mayBeFoundRestaurant.isEmpty()) {
            return restaurantRepository.save(restaurant);
        }
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();
        Long seq = foundRestaurant.getSeq();
        Restaurant restaurantToBeUploaded = new Restaurant(seq, restaurant);

        return restaurantRepository.save(restaurantToBeUploaded);
    }

    public List<RestaurantResponse> search(RestaurantRequest restaurantRequest) {
        return restaurantRepository.findAllRestaurantsByRequest(restaurantRequest);
    }

    public List<RestaurantResponse> myLocationBasedSearch(String mobilityAsString, PrincipalDto principal) {
        Mobility mobility = Mobility.parseMobility(mobilityAsString);
        System.out.println(principal.latitude());
        System.out.println(principal.longitude());
        if (mobility == null) {
            throw new IllegalArgumentException("잘못된 이동수단을 입력했습니다.");
        }
        RestaurantRequest restaurantRequest = RestaurantRequest.builder()
                .latitude(principal.latitude())
                .longitude(principal.longitude())
                .range(mobility.range)
                .build();
        return restaurantRepository.findAllRestaurantsByRequest(restaurantRequest);
    }

    public List<RestaurantResponse> regionNameBasedSearch(RegionRequest regionRequest) throws IOException {
        Double rangeInKiloMeter = 10.0;
        Coordinates coordinates = dosiSggFinder.findCoordinatesByDosiAndSgg(regionRequest.dosi(),
                regionRequest.sgg());
        RestaurantRequest restaurantRequest = RestaurantRequest.builder()
                .latitude(coordinates.latitudeInDegrees())
                .longitude(coordinates.longitudeInDegrees())
                .range(rangeInKiloMeter)
                .build();
        return restaurantRepository.findAllRestaurantsByRequest(restaurantRequest);
    }

    public RestaurantDetailResponse getDetailById(Long id) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(id);
        if (mayBeFoundRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("맛집 상세 정보를 열람할 수 없습니다.");
        }
        Restaurant restaurant = mayBeFoundRestaurant.get();
        return restaurant.toDetailResponse();
    }

    public List<ReviewShortResponse> getReviewsById(Long id) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(id);
        if (mayBeFoundRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("해당 맛집을 찾을 수 없습니다.");
        }
        List<Review> reviewList = mayBeFoundRestaurant.get().getReviews();
        return reviewList.stream()
                .map(Review::toShortResponse)
                .toList();
    }

}