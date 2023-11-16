package com.wandted.matitnyam.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wandted.matitnyam.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.wandted.matitnyam.domain.entity.Restaurant;
import com.wandted.matitnyam.repository.querydsl.RestaurantRepositoryQueryDsl;

/**
 * @author 민경수
 * @description restaurant repository impl
 * @since 2023.11.02
 **********************************************************************************************************************/
public class RestaurantRepositoryImpl extends QueryDslRepositoryPaginationSupport implements
        RestaurantRepositoryQueryDsl {

    public RestaurantRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Restaurant.class, queryFactory);
    }

}