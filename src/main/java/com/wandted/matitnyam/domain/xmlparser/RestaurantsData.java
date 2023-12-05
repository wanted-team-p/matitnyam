package com.wandted.matitnyam.domain.xmlparser;

import com.wandted.matitnyam.domain.Restaurant;
import java.util.List;

public interface RestaurantsData {

    MetaInformation getMetaInformation();

    List<Restaurant> getRestaurants();

    RestaurantsData preprocess();

}