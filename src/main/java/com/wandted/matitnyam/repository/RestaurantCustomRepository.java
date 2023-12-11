package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Restaurant;
import com.wandted.matitnyam.dto.RestaurantDto;
import com.wandted.matitnyam.dto.RestaurantRequest;
import java.util.List;
import java.util.Optional;

public interface RestaurantCustomRepository {

    Optional<Restaurant> findByNameAndAddressAsRoadName(String name, String addressAsRoadName);

    List<RestaurantDto> findAllRestaurantsByRequest(RestaurantRequest restaurantRequest);

}
