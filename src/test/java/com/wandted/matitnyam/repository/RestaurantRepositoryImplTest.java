package com.wandted.matitnyam.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wandted.matitnyam.domain.Coordinates;
import com.wandted.matitnyam.domain.Restaurant;
import com.wandted.matitnyam.dto.RestaurantDto;
import com.wandted.matitnyam.dto.RestaurantRequest;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:test/h2.sql")
@DataJpaTest
class RestaurantRepositoryImplTest {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void findByNameAndAddressAsRoadNameTest() {
        String name = "삼국지";
        String addressAsRoadName = "경기도 용인시 기흥구 한보라2로14번길 3-7 (보라동)";
        Double latitude = 37.2539499121;
        Double longitude = 127.1119282508;
        Optional<Restaurant> mayBeFoundRestaurant = restaurantRepository.findByNameAndAddressAsRoadName(name, addressAsRoadName);
        Restaurant foundRestaurant = mayBeFoundRestaurant.get();
        Assertions.assertThat(foundRestaurant.getName()).isEqualTo(name);
        Assertions.assertThat(foundRestaurant.getLatitude()).isEqualTo(latitude);
        Assertions.assertThat(foundRestaurant.getLongitude()).isEqualTo(longitude);
    }

    @Test
    void findAllRestaurantsByRequestTest() throws JsonProcessingException {
        double latitude = 37.2040;
        double longitude = 127.07596008849987;
        String range = "6.5";

        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        List<Restaurant> restaurantsWithinRange = allRestaurants.stream()
                .filter(restaurant -> {
                    Coordinates source = Coordinates.builder()
                            .latitudeInDegrees(latitude)
                            .longitudeInDegrees(longitude)
                            .build();

                    Coordinates destination = Coordinates.builder()
                            .latitudeInDegrees(restaurant.getLatitude())
                            .longitudeInDegrees(restaurant.getLongitude())
                            .build();
                    double distance = Coordinates.calculateDistance(source, destination);
                    System.out.print("맛집 이름:" + restaurant.getName() + ", ");
                    System.out.println("거리: " + distance + " km");
                    return distance <= Double.parseDouble(range);
                })
                .toList();

        RestaurantRequest restaurantRequest = RestaurantRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .range(range)
                .build();
        List<RestaurantDto> resultList = restaurantRepository.findAllRestaurantsByRequest(restaurantRequest);
        for (RestaurantDto restaurantDto : resultList) {
            String restaurantDtoInJsonString = objectWriter.writeValueAsString(restaurantDto);
            System.out.println(restaurantDtoInJsonString);
        }

        Assertions.assertThat(restaurantsWithinRange.size()).isEqualTo(resultList.size());
    }

}
