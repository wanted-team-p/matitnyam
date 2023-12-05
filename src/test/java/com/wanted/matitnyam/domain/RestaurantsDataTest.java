package com.wanted.matitnyam.domain;

import com.wanted.matitnyam.domain.xmlparser.RestaurantsDataParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantsDataTest {

    @Autowired
    RestaurantsDataParser restaurantsDataParser;

    @Test
    void preprocessTest() {
        String testFilePath = "data/xml-test-data.xml";
        RestaurantsData restaurantsData = restaurantsDataParser.parse(ChineseRestaurants.class, testFilePath);
        restaurantsData.preprocess();
        Assertions
                .assertThat(restaurantsData.getRestaurants().size())
                .isEqualTo(2);
    }

}