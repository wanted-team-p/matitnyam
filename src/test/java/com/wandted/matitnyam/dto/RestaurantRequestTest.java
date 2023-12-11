package com.wandted.matitnyam.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RestaurantRequestTest {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private final Double latitude = 37.74913611;

    private final Double longitude = 128.8784972;

    private final String range = "3.0";

    @Test
    void nullInputForSortTypeTest() throws JsonProcessingException {
        RestaurantRequest restaurantRequest = RestaurantRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .range(range)
                .build();
        Assertions
                .assertThat(restaurantRequest.getSortType())
                .isEqualTo(RestaurantSortType.DISTANCE);
        String restaurantRequestInJson = objectWriter.writeValueAsString(restaurantRequest);
        System.out.println(restaurantRequestInJson);
    }

    @Test
    void ExpectedBehaviorTest() throws JsonProcessingException {
        RestaurantRequest restaurantRequest = RestaurantRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .range(range)
                .sortType("평점순")
                .build();
        Assertions
                .assertThat(restaurantRequest.getSortType())
                .isEqualTo(RestaurantSortType.RATE);
        String restaurantRequestInJson = objectWriter.writeValueAsString(restaurantRequest);
        System.out.println(restaurantRequestInJson);
    }

}