package com.wandted.matitnyam.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wandted.matitnyam.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.wandted.matitnyam.domain.entity.Review;
import com.wandted.matitnyam.repository.querydsl.ReviewRepositoryQueryDsl;
import java.util.Optional;

/**
 * @author 민경수
 * @description review repository impl
 * @since 2023.11.02
 **********************************************************************************************************************/
public class ReviewRepositoryImpl extends QueryDslRepositoryPaginationSupport implements ReviewRepositoryQueryDsl {

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Review.class, queryFactory);
    }

    @Override
    public Optional<Review> findBy(Long userSeq, Long restaurantSeq) {
        return null;
    }

}