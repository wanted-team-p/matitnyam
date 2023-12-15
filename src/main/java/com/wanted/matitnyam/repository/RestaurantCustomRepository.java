package com.wanted.matitnyam.repository;

import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.dto.RestaurantDto;
import com.wanted.matitnyam.dto.RestaurantRequest;
import java.util.List;
import java.util.Optional;

public interface RestaurantCustomRepository {

    Optional<Restaurant> findByNameAndAddressAsRoadName(String name, String addressAsRoadName);

    List<RestaurantDto> findAllRestaurantsByRequest(RestaurantRequest restaurantRequest);

}
