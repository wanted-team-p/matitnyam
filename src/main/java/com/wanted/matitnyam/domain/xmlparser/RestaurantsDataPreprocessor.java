package com.wanted.matitnyam.domain.xmlparser;

import com.wanted.matitnyam.domain.Restaurant;

public class RestaurantsDataPreprocessor {

    public static boolean hasEssentialInformation(Restaurant restaurant) {
        if (restaurant.getCityName() == null) {
            return false;
        }
        if (restaurant.getRestaurantName() == null) {
            return false;
        }
        if (restaurant.getCloseOrOpen().equals("폐업")) {
            return false;
        }
        if (restaurant.getTypeOfFoods() == null) {
            return false;
        }
        if (restaurant.getAddressAsLocationName() == null && restaurant.getAddressAsRoadName() == null) {
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
