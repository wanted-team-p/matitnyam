package com.wanted.matitnyam.domain.xmlparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantsDataParserTest {

    @Autowired
    RestaurantsDataParser restaurantsDataParser;

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @DisplayName("중식 음식점 파싱 테스트")
    @Test
    void chineseRestaurantDataParseTest() throws IOException {
        String testFilePath = "data/chinese-restaurant-test.xml";
        RestaurantsData restaurantsData = restaurantsDataParser.parse(ChineseRestaurants.class, testFilePath);
        String restaurantDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(restaurantDataInJson);
    }

    @DisplayName("일식 음식점 파싱 테스트")
    @Test
    void japaneseRestaurantDataParseTest() throws IOException {
        String testFilePath = "data/japanese-restaurant-test.xml";
        RestaurantsData restaurantsData = restaurantsDataParser.parse(JapaneseRestaurants.class, testFilePath);
        String restaurantDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(restaurantDataInJson);
    }

    @DisplayName("카페 파싱 테스트")
    @Test
    void cafesDataParseTest() throws IOException {
        String testFilePath = "data/cafe-test.xml";
        RestaurantsData restaurantsData = restaurantsDataParser.parse(Cafes.class, testFilePath);
        String restaurantDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(restaurantDataInJson);
    }

}