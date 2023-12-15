package com.wanted.matitnyam.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantRequestTest {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private final Double latitude = 37.74913611;

    private final Double longitude = 128.8784972;

    private final Double range = 3.0;

    @DisplayName("정렬 기준이 없는 경우 맛집 리스트 조회 요청 테스트: 정렬 기준이 거리순으로 설정된다.")
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

    @DisplayName("맛집 리스트 조회 시 정렬 기준이 '평점순'인 경우 맛집 리스트 조회 요청 테스트: 정렬 기준은 평점순으로 설정된다.")
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