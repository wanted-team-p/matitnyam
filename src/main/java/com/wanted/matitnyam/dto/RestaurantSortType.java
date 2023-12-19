package com.wanted.matitnyam.dto;

public enum RestaurantSortType {

    DISTANCE("거리순"),
    RATE("평점순");

    public final String sortType;

    RestaurantSortType(final String sortType) {
        this.sortType = sortType;
    }

    public static RestaurantSortType convertTo(String givenSortTypeString) {
        for (RestaurantSortType restaurantSortType : RestaurantSortType.values()) {
            if (restaurantSortType.sortType.equals(givenSortTypeString)) {
                return restaurantSortType;
            }
        }
        return null;
    }

}
