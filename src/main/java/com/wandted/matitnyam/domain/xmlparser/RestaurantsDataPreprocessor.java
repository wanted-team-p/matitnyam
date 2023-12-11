package com.wandted.matitnyam.domain.xmlparser;

import com.wandted.matitnyam.domain.Restaurant;

public class RestaurantsDataPreprocessor {

    public static boolean hasEssentialInformation(Restaurant restaurant) {
        if (restaurant.getCity().isBlank()) {
            return false;
        }
        if (restaurant.getName().isBlank()) {
            return false;
        }
        if (restaurant.getTypeOfFoods().isBlank()) {
            return false;
        }
        if (restaurant.getAddressAsLocationName().isBlank() && restaurant.getAddressAsRoadName().isBlank()) {
            return false;
        }
        if (restaurant.getLatitude() == null) {
            return false;
        }
        if (restaurant.getLongitude() == null) {
            return false;
        }
        return true;
    }

}
