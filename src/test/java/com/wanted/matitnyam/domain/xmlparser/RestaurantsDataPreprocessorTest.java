package com.wanted.matitnyam.domain.xmlparser;

import com.wanted.matitnyam.domain.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantsDataPreprocessorTest {

    String city = "용인시";
    String name = "삼국지";
    String closeOrOpen = "영업";
    String typeOfFoods = "중국식";
    String addressAsRoadName = "경기도 용인시 기흥구 한보라2로14번길 3-7 (보라동)";
    String addressAsLocationName = "경기도 용인시 기흥구 보라동 586-4";
    double latitude = 37.2539499121;
    double longitude = 127.1119282508;

    @DisplayName("전처리 로직 테스트 1: 정상동작")
    @Test
    void hasEssentialInformationTest1() {
        Restaurant restaurant = Restaurant.builder()
                .city(city)
                .name(name)
                .closeOrOpen(closeOrOpen)
                .typeOfFoods(typeOfFoods)
                .addressAsLocationName(addressAsLocationName)
                .addressAsRoadName(addressAsRoadName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        Assertions
                .assertThat(RestaurantsDataPreprocessor.hasEssentialInformation(restaurant))
                .isTrue();
    }

    @DisplayName("전처리 로직 테스트 2: 시 정보가 없는 경우")
    @Test
    void hasEssentialInformationTest2() {
        Restaurant restaurant = Restaurant.builder()
                .city("")
                .name(name)
                .closeOrOpen(closeOrOpen)
                .typeOfFoods(typeOfFoods)
                .addressAsLocationName(addressAsLocationName)
                .addressAsRoadName(addressAsRoadName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        Assertions
                .assertThat(RestaurantsDataPreprocessor.hasEssentialInformation(restaurant))
                .isFalse();
    }

    @DisplayName("전처리 로직 테스트 3: 도로명 주소만 있는 경우")
    @Test
    void hasEssentialInformationTest3() {
        Restaurant restaurant = Restaurant.builder()
                .city(city)
                .name(name)
                .closeOrOpen(closeOrOpen)
                .typeOfFoods(typeOfFoods)
                .addressAsLocationName("")
                .addressAsRoadName(addressAsRoadName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        Assertions
                .assertThat(RestaurantsDataPreprocessor.hasEssentialInformation(restaurant))
                .isTrue();
    }

}