package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Restaurant;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void findByNameLatitudeAndLongitudeTest() {
        String name = "삼국지";
        String latitude = "37.2539499121";
        String longitude = "127.1119282508";
        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        restaurantRepository.save(restaurant);
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findByNameAndCoordinates(name, latitude,
                longitude);
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();
        Assertions.assertThat(foundRestaurant.getName()).isEqualTo(name);
        Assertions.assertThat(foundRestaurant.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(foundRestaurant.getLongitude()).isEqualTo(longitude);
    }

}
