package com.wanted.matitnyam.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wanted.matitnyam.domain.xmlparser.ChineseRestaurants;
import com.wanted.matitnyam.domain.xmlparser.RestaurantsData;
import com.wanted.matitnyam.domain.xmlparser.RestaurantsDataParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantsDataTest {

    @Autowired
    RestaurantsDataParser restaurantsDataParser;

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    void preprocessTest() throws JsonProcessingException {
        String testFilePath = "data/chinese-restaurant-test.xml";
        RestaurantsData restaurantsData = restaurantsDataParser
                .parse(ChineseRestaurants.class, testFilePath);
        String restaurantsDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(restaurantsDataInJson);
        System.out.println();

        restaurantsData = restaurantsData.preprocess();
        String processedDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(processedDataInJson);

        Assertions
                .assertThat(restaurantsData.getRestaurants().size())
                .isEqualTo(2);
    }

}