package com.wandted.matitnyam.restaurant.domain;

import org.springframework.data.jpa.domain.Specification;

public class RestaurantSepcification {

    public static Specification<Restaurant> isNearby(long lat, long lon, long range){
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                  criteriaBuilder.between(root.get("longitude"), lon - range, lon + range),
                  criteriaBuilder.between(root.get("latitude"), lat - range, lat + range)
          );
    }
}
