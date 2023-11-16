package com.wandted.matitnyam.repository.impl;

import static com.wandted.matitnyam.domain.entity.QRegion.region;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wandted.matitnyam.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.wandted.matitnyam.domain.entity.QRegion;
import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.repository.querydsl.RegionRepositoryQueryDsl;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.lang.Nullable;

/**
 * @author 민경수
 * @description region repository impl
 * @since 2023.11.02
 **********************************************************************************************************************/
public class RegionRepositoryImpl extends QueryDslRepositoryPaginationSupport implements RegionRepositoryQueryDsl {

    public RegionRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Region.class, queryFactory);
    }

    @Override
    public List<Region> findAllBy(String city, String district) {
        JPQLQuery<Region> query = findAll(existsCity(city), existsDistrict(district));

        return query.fetch();
    }

    private JPQLQuery<Region> findAll(Predicate... where) {
        //@formatter:off
        return from(region)
                .where(where);
        //@formatter:on
    }

    private BooleanExpression existsCity(String city) {
        return city.isBlank() ? null : region.city.contains(city);
    }

    private BooleanExpression existsDistrict(String district) {
        return district.isBlank() ? null : region.district.contains(district);
    }

}