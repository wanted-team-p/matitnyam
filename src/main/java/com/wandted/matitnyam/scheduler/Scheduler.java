package com.wandted.matitnyam.scheduler;

import com.wandted.matitnyam.domain.xmlparser.Cafes;
import com.wandted.matitnyam.domain.xmlparser.ChineseRestaurants;
import com.wandted.matitnyam.domain.xmlparser.JapaneseRestaurants;
import com.wandted.matitnyam.domain.xmlparser.RestaurantsData;
import com.wandted.matitnyam.domain.xmlparser.RestaurantsDataParser;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Scheduler {

    private final OpenApiStreamGetter openApiStreamGetter;

    private final RestaurantsDataParser restaurantsDataParser;

    private final RestaurantsDataUploader restaurantsDataUploader;

    @Value("${open-api.key-url}")
    private String keyUrl;

    @Value("${open-api.chinese-restaurant.api-url}")
    private String chineseRestaurantUrl;

    @Value("${open-api.japanese-restaurant.api-url}")
    private String japaneseRestaurantUrl;

    @Value("${open-api.cafe.api-url}")
    private String cafeUrl;

    @Scheduled(cron = "*/10 * * * * *")
    public void collect() throws IOException, JAXBException {
        collect(ChineseRestaurants.class, chineseRestaurantUrl + keyUrl);
        collect(JapaneseRestaurants.class, japaneseRestaurantUrl + keyUrl);
        collect(Cafes.class, cafeUrl + keyUrl);
    }

    private void collect(Class<?> restaurantsDataClass, String openApiUrlAndKeyInString) throws IOException, JAXBException {
        InputStream restaurantsInputStream = openApiStreamGetter.get(openApiUrlAndKeyInString);
        RestaurantsData restaurantsData = restaurantsDataParser
                .parse(restaurantsDataClass, restaurantsInputStream)
                .preprocess();
        restaurantsDataUploader.upload(restaurantsData);
    }

}
