package com.wandted.matitnyam.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wandted.matitnyam.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.repository.querydsl.RegionRepositoryQueryDsl;

/**
 * @author 민경수
 * @description region repository impl
 * @since 2023.11.02
 **********************************************************************************************************************/
public class RegionRepositoryImpl extends QueryDslRepositoryPaginationSupport implements RegionRepositoryQueryDsl {

    public RegionRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Region.class, queryFactory);
    }

}