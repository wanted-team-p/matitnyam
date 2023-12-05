package com.wandted.matitnyam.domain;

import com.wandted.matitnyam.domain.xmlparser.MetaInformation;
import com.wandted.matitnyam.domain.xmlparser.Restaurant;
import java.util.List;

public interface RestaurantsData {

    MetaInformation getMetaInformation();

    List<Restaurant> getRestaurants();

    void preprocess();

}