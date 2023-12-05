package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<Restaurant> findByNameAndCoordinates(String name, String latitude, String longitude) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = builder.createQuery(Restaurant.class);
        Root<Restaurant> restaurant = query.from(Restaurant.class);

        Predicate hasSameName = builder.equal(restaurant.get("name"), builder.literal(name));
        Predicate hasSameLatitude = builder.equal(restaurant.get("latitude"), builder.literal(latitude));
        Predicate hasSameLongitude = builder.equal(restaurant.get("longitude"), builder.literal(longitude));
        query.where(builder.and(hasSameName, hasSameLatitude, hasSameLongitude));

        return entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
    }

}
