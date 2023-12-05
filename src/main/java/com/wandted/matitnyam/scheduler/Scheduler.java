package com.wandted.matitnyam.scheduler;

import com.wandted.matitnyam.domain.RestaurantsDataPreprocessor;
import com.wandted.matitnyam.domain.xmlparser.RestaurantsDataParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Scheduler {

    private final OpenApiCollector openApiCollector;

    private final RestaurantsDataParser restaurantsDataParser;

    private final RestaurantsDataPreprocessor restaurantsDataPreprocessor;

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
    }

}
