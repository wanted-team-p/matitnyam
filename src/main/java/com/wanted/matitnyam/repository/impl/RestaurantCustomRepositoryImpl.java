package com.wanted.matitnyam.repository.impl;

import com.wanted.matitnyam.domain.Restaurant;
import com.wanted.matitnyam.dto.RestaurantDto;
import com.wanted.matitnyam.dto.RestaurantRequest;
import com.wanted.matitnyam.dto.RestaurantSortType;
import com.wanted.matitnyam.repository.RestaurantCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    private final EntityManager entityManager;

    double radiusOfEarthInKiloMeter = 6371;

    @Override
    public Optional<Restaurant> findByNameAndAddressAsRoadName(String name, String addressAsRoadName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = builder.createQuery(Restaurant.class);
        Root<Restaurant> restaurant = query.from(Restaurant.class);

        Predicate hasSameName = builder.equal(restaurant.get("name"), builder.literal(name));
        Predicate hasSameAddress = builder.equal(restaurant.get("addressAsRoadName"),
                builder.literal(addressAsRoadName));
        query.where(builder.and(hasSameName, hasSameAddress));

        return entityManager
                .createQuery(query)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<RestaurantDto> findAllRestaurantsByRequest(RestaurantRequest restaurantRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RestaurantDto> query = builder.createQuery(RestaurantDto.class);
        Root<Restaurant> restaurant = query.from(Restaurant.class);

        Expression<Double> arcLengthExpression = getArcLengthExpression(builder, restaurant, restaurantRequest);
        Expression<Double> rangeAsExpression = builder.literal(restaurantRequest.getRange());

        query.select(builder.construct(RestaurantDto.class, restaurant.get("name"), restaurant.get("closeOrOpen"),
                restaurant.get("typeOfFoods"), restaurant.get("addressAsRoadName"), restaurant.get("rating"),
                arcLengthExpression));
        query.where(builder.lessThanOrEqualTo(arcLengthExpression, rangeAsExpression));
        if (restaurantRequest.getSortType().equals(RestaurantSortType.RATE)) {
            query.orderBy(builder.desc(restaurant.get("rating")));
        } else {
            query.orderBy(builder.asc(arcLengthExpression));
        }

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    private Expression<Double> getArcLengthExpression(CriteriaBuilder builder, Root<Restaurant> restaurant,
                                                      RestaurantRequest restaurantRequest) {
        Expression<Double> destinationLatitudeInRadiansExpression = builder.function("radians", Double.class,
                builder.toDouble(restaurant.get("latitude")));
        Expression<Double> destinationLongitudeInRadiansExpression = builder.function("radians", Double.class,
                builder.toDouble(restaurant.get("longitude")));

        double sourceLatitudeInRadians = Math.toRadians(restaurantRequest.getLatitude());
        double sourceLongitudeInRadians = Math.toRadians(restaurantRequest.getLongitude());
        Expression<Double> sourceLatitudeInRadiansExpression = builder.literal(sourceLatitudeInRadians);
        Expression<Double> sourceLongitudeInRadiansExpression = builder.literal(sourceLongitudeInRadians);

        Expression<Double> xCoordinateProductExpression = getXCoordinateProductExpression(builder,
                sourceLatitudeInRadiansExpression, sourceLongitudeInRadiansExpression,
                destinationLatitudeInRadiansExpression, destinationLongitudeInRadiansExpression);

        Expression<Double> yCoordinateProductExpression = getYCoordinateProductExpression(builder,
                sourceLatitudeInRadiansExpression, sourceLongitudeInRadiansExpression,
                destinationLatitudeInRadiansExpression, destinationLongitudeInRadiansExpression);

        Expression<Double> zCoordinateProductExpression = getZCoordinateProductExpression(builder,
                sourceLatitudeInRadiansExpression, destinationLatitudeInRadiansExpression);

        Expression<Double> arcLengthOfUnitSphereExpression = getArcLengthOfUnitSphereExpression(builder,
                xCoordinateProductExpression, yCoordinateProductExpression, zCoordinateProductExpression);

        return builder.prod(arcLengthOfUnitSphereExpression, radiusOfEarthInKiloMeter);
    }

    private Expression<Double> getXCoordinateProductExpression(CriteriaBuilder builder,
                                                               Expression<Double> sourceLatitudeInRadiansExpression,
                                                               Expression<Double> sourceLongitudeInRadiansExpression,
                                                               Expression<Double> destinationLatitudeInRadiansExpression,
                                                               Expression<Double> destinationLongitudeInRadiansExpression) {
        Expression<Double> destinationXCoordinateExpression = getXCoordinateExpression(builder,
                destinationLatitudeInRadiansExpression, destinationLongitudeInRadiansExpression);
        Expression<Double> sourceXCoordinateExpression = getXCoordinateExpression(builder,
                sourceLatitudeInRadiansExpression, sourceLongitudeInRadiansExpression);
        return builder.prod(destinationXCoordinateExpression, sourceXCoordinateExpression);
    }

    private Expression<Double> getXCoordinateExpression(CriteriaBuilder builder, Expression<Double> latitudeInRadians,
                                                        Expression<Double> longitudeInRadians) {
        return builder.prod(builder.function("cos", Double.class, latitudeInRadians),
                builder.function("cos", Double.class, longitudeInRadians));
    }

    private Expression<Double> getYCoordinateProductExpression(CriteriaBuilder builder,
                                                               Expression<Double> sourceLatitudeInRadiansExpression,
                                                               Expression<Double> sourceLongitudeInRadiansExpression,
                                                               Expression<Double> destinationLatitudeInRadiansExpression,
                                                               Expression<Double> destinationLongitudeInRadiansExpression) {
        Expression<Double> destinationYCoordinateExpression = getYCoordinateExpression(builder,
                destinationLatitudeInRadiansExpression, destinationLongitudeInRadiansExpression);
        Expression<Double> sourceYCoordinateExpression = getYCoordinateExpression(builder,
                sourceLatitudeInRadiansExpression, sourceLongitudeInRadiansExpression);
        return builder.prod(destinationYCoordinateExpression, sourceYCoordinateExpression);
    }

    private Expression<Double> getYCoordinateExpression(CriteriaBuilder builder, Expression<Double> latitudeInRadians,
                                                        Expression<Double> longitudeInRadians) {
        return builder.prod(builder.function("cos", Double.class, latitudeInRadians),
                builder.function("sin", Double.class, longitudeInRadians));
    }

    private Expression<Double> getZCoordinateProductExpression(CriteriaBuilder builder,
                                                               Expression<Double> sourceLatitudeInRadiansExpression,
                                                               Expression<Double> destinationLatitudeInRadiansExpression) {
        Expression<Double> destinationZCoordinateExpression = getZCoordinateExpression(builder,
                destinationLatitudeInRadiansExpression);
        Expression<Double> sourceZCoordinateExpression = getZCoordinateExpression(builder,
                sourceLatitudeInRadiansExpression);
        return builder.prod(destinationZCoordinateExpression, sourceZCoordinateExpression);
    }

    private Expression<Double> getZCoordinateExpression(CriteriaBuilder builder, Expression<Double> latitudeInRadians) {
        return builder.function("sin", Double.class, latitudeInRadians);
    }

    private Expression<Double> getArcLengthOfUnitSphereExpression(CriteriaBuilder builder,
                                                                  Expression<Double> xCoordinateProduct,
                                                                  Expression<Double> yCoordinateProduct,
                                                                  Expression<Double> zCoordinateProduct) {
        Expression<Double> xyzCoordinateSum = builder.sum(xCoordinateProduct, yCoordinateProduct);
        xyzCoordinateSum = builder.sum(xyzCoordinateSum, zCoordinateProduct);
        return builder.function("acos", Double.class, xyzCoordinateSum);
    }

}
