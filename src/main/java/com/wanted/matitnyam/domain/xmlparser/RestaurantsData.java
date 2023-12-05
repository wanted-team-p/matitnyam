package com.wanted.matitnyam.domain.xmlparser;

import com.wanted.matitnyam.domain.Restaurant;
import java.util.List;

public interface RestaurantsData {

    MetaInformation getMetaInformation();

    List<Restaurant> getRestaurants();

    RestaurantsData preprocess();

}