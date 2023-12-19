package com.wanted.matitnyam.scheduler;

import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.domain.xmlparser.RestaurantsData;
import com.wanted.matitnyam.service.RestaurantService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantsDataUploader {

    private final RestaurantService restaurantService;

    public void upload(RestaurantsData restaurantsData) {
        List<Restaurant> restaurantList = restaurantsData.getRestaurants();
        for (Restaurant restaurant : restaurantList) {
            restaurantService.upload(restaurant);
        }
    }

}
