package com.wanted.matitnyam.domain.xmlparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wanted.matitnyam.domain.ChineseRestaurants;
import com.wanted.matitnyam.domain.RestaurantsData;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantsDataParserTest {

    @Autowired
    RestaurantsDataParser restaurantsDataParser;

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    void parseTest() throws IOException {
        String testFilePath = "data/xml-test-data.xml";
        RestaurantsData restaurantsData = restaurantsDataParser.parse(ChineseRestaurants.class, testFilePath);
        String restaurantDataInJson = objectWriter.writeValueAsString(restaurantsData);
        System.out.println(restaurantDataInJson);
    }

}