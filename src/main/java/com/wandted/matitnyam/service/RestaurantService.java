package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Restaurant;
import com.wandted.matitnyam.repository.RestaurantRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant upload(Restaurant restaurant) {
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findByNameAndCoordinates(restaurant.getName(),
                restaurant.getLatitude(), restaurant.getLongitude());
        if (mayBeFoundRestaurant.isEmpty()) {
            return restaurantRepository.save(restaurant);
        }
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();
        Long seq = foundRestaurant.getSeq();
        Restaurant restaurantToBeUploaded = new Restaurant(seq, restaurant);
        return restaurantRepository.save(restaurantToBeUploaded);
    }

}
