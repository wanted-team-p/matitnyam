package com.wandted.matitnyam.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wandted.matitnyam.common.configuration.jpa.querydsl.support.QueryDslRepositoryPaginationSupport;
import com.wandted.matitnyam.domain.entity.User;
import com.wandted.matitnyam.repository.querydsl.UserRepositoryQueryDsl;

/**
 * @author 민경수
 * @description user repository impl
 * @since 2023.11.02
 **********************************************************************************************************************/
public class UserRepositoryImpl extends QueryDslRepositoryPaginationSupport implements UserRepositoryQueryDsl {

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class, queryFactory);
    }

}