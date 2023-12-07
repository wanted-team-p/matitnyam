package com.wandted.matitnyam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.domain.Restaurant;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    final static ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    void uploadTest() throws JsonProcessingException {
        String name = "삼국지";
        String latitude = "37.2539499121";
        String longitude = "127.1119282508";
        String isOpen = "영업";
        String isClose = "폐업";

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .closeOrOpen(isOpen)
                .build();
        restaurantService.upload(restaurant);
        String restaurantInformationAsString = objectWriter.writeValueAsString(restaurant);
        System.out.println(restaurantInformationAsString);

        Restaurant changedRestaurant = Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .closeOrOpen(isClose)
                .build();
        Restaurant returnedRestaurant = restaurantService.upload(changedRestaurant);
        String changedRestaurantInformationAsString = objectWriter.writeValueAsString(returnedRestaurant);
        System.out.println(changedRestaurantInformationAsString);

        Assertions.assertThat(restaurant.getSeq()).isEqualTo(returnedRestaurant.getSeq());
    }

    @Test
    void findRegionByDosiTest() throws IOException {
        String gyeonggi = "경기";
        Region region = Region.builder()
                .dosi(gyeonggi)
                .build();
        List<String> sggList = restaurantService.findRegion(region);
        for (String sgg : sggList) {
            System.out.print(sgg + ' ');
        }
    }

    @Test
    void findRegionBySggTest() throws IOException {
        String dongu = "동구";
        Region region = Region.builder()
                .sgg(dongu)
                .build();
        List<String> dosiList = restaurantService.findRegion(region);
        for (String dosi : dosiList) {
            System.out.print(dosi + ' ');
        }
    }

}