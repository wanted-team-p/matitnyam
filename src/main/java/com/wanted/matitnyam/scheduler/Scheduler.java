package com.wanted.matitnyam.scheduler;

import com.wanted.matitnyam.domain.Cafes;
import com.wanted.matitnyam.domain.ChineseRestaurants;
import com.wanted.matitnyam.domain.JapaneseRestaurants;
import com.wanted.matitnyam.domain.RestaurantsData;
import com.wanted.matitnyam.domain.xmlparser.RestaurantsDataParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Scheduler {

    private final OpenApiCollector openApiCollector;

    private final RestaurantsDataParser restaurantsDataParser;

    @Value("${open-api.chinese-restaurant.api-url}")
    private String chineseRestaurantUrl;

    @Value("${open-api.chinese-restaurant.file}")
    private String chineseRestaurantFileUrl;

    @Value("${open-api.japanese-restaurant.api-url}")
    private String japaneseRestaurantUrl;

    @Value("${open-api.japanese-restaurant.file}")
    private String japaneseRestaurantFileUrl;

    @Value("${open-api.cafe.api-url}")
    private String cafeUrl;

    @Value("${open-api.cafe.file}")
    private String cafeFileUrl;

    @Scheduled(cron = "*/10 * * * * *")
    public void run() {
        openApiCollector.collect(chineseRestaurantUrl, chineseRestaurantFileUrl);
        openApiCollector.collect(japaneseRestaurantUrl, japaneseRestaurantFileUrl);
        openApiCollector.collect(cafeUrl, cafeFileUrl);
        RestaurantsData chineseRestaurants = restaurantsDataParser
                .parse(ChineseRestaurants.class, chineseRestaurantFileUrl)
                .preprocess();
        RestaurantsData japaneseRestaurants = restaurantsDataParser
                .parse(JapaneseRestaurants.class, japaneseRestaurantFileUrl)
                .preprocess();
        RestaurantsData cafes = restaurantsDataParser
                .parse(Cafes.class, cafeFileUrl)
                .preprocess();
        // TODO: 파싱한 데이터 저장 기능 구현.
    }

}
