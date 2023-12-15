package com.wandted.matitnyam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.domain.Restaurant;
import com.wandted.matitnyam.dto.RegionRequest;
import com.wandted.matitnyam.dto.RestaurantDetailDto;
import com.wandted.matitnyam.dto.RestaurantDto;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class RestaurantServiceTest {

    final static ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private RestaurantService restaurantService;

    @DisplayName("맛집 정보 업로드 테스트")
    @Rollback
    @Test
    void uploadTest() throws JsonProcessingException {
        String name = "삼국지";
        Double latitude = 37.2539499121;
        Double longitude = 127.1119282508;
        String isOpen = "영업";
        String isClose = "폐업";
        String addressAsRoadName = "경기도 용인시 기흥구 한보라2로14번길 3-7 (보라동)";

        Restaurant initialRestaurant = Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .closeOrOpen(isOpen)
                .addressAsRoadName(addressAsRoadName)
                .build();
        Restaurant returnedInitialRestaurant = restaurantService.upload(initialRestaurant);
        String uploadedInitialRestaurantAsString = objectWriter.writeValueAsString(
                returnedInitialRestaurant);
        System.out.println(uploadedInitialRestaurantAsString);

        Restaurant changedRestaurant = Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .closeOrOpen(isClose)
                .addressAsRoadName(addressAsRoadName)
                .build();
        Restaurant returnedChangedRestaurant = restaurantService.upload(changedRestaurant);
        String returnedRestaurantInformationAsString = objectWriter.writeValueAsString(returnedChangedRestaurant);
        System.out.println(returnedRestaurantInformationAsString);

        Assertions
                .assertThat(returnedInitialRestaurant.getSeq())
                .isEqualTo(returnedChangedRestaurant.getSeq());
    }

    @DisplayName("시/도, 시군구 정보로 맛집 목록 조회 테스트")
    @Test
    @Sql(value = "classpath:test/h2.sql")
    void regionNameBasedSearchTest() throws IOException {
        String dosi = "경기";
        String sgg = "용인시";
        RegionRequest regionRequest = RegionRequest.builder()
                .dosi(dosi)
                .sgg(sgg)
                .build();
        List<RestaurantDto> returnedList = restaurantService.regionNameBasedSearch(regionRequest);
        for (RestaurantDto restaurantDto : returnedList) {
            String restaurantDtoAsString = objectWriter.writeValueAsString(restaurantDto);
            System.out.println(restaurantDtoAsString);
        }
    }

    @DisplayName("맛집 상세 정보 조회 테스트")
    @Test
    @Sql(value = "classpath:test/h2.sql")
    void searchTest() throws JsonProcessingException {
        RestaurantDetailDto restaurantDetailDto = restaurantService.getDetailById(1L);
        String valueAsString = objectWriter.writeValueAsString(restaurantDetailDto);
        System.out.println(valueAsString);
    }

    @DisplayName("시도 정보를 통한 시군구 리스트 조회 테스트")
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

    @DisplayName("시군구 정보를 통한 시도 리스트 조회 테스트")
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