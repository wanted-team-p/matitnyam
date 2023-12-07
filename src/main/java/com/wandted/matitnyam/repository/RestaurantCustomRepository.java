package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Restaurant;
import java.util.Optional;

public interface RestaurantCustomRepository {

    Optional<Restaurant> findByNameAndCoordinates(String name, String latitude, String longitude);

}
