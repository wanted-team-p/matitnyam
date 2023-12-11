package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.domain.Restaurant;
import com.wandted.matitnyam.dto.RestaurantDetailDto;
import com.wandted.matitnyam.dto.RestaurantDto;
import com.wandted.matitnyam.dto.RestaurantRequest;
import com.wandted.matitnyam.exception.ResourceNotFoundException;
import com.wandted.matitnyam.repository.RestaurantRepository;
import com.wandted.matitnyam.util.DosiSggFinder;
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

    public List<String> findRegion(Region region) throws IOException {
        if (region.getSgg() != null) {
            return dosiSggFinder.findDosiList(region.getSgg());
        }
        return dosiSggFinder.findSggList(region.getDosi());
    }

    public List<RestaurantDto> search(RestaurantRequest restaurantRequest) {
        return restaurantRepository.findAllRestaurantsByRequest(restaurantRequest);
    }

    public RestaurantDetailDto getDetailById(Long id) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findById(id);
        if (mayBeFoundRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("레스토랑 상세 정보를 열람할 수 없습니다.");
        }
        Restaurant restaurant = mayBeFoundRestaurant.get();
        return restaurant.toDetailDtoWithDistance();
    }

}