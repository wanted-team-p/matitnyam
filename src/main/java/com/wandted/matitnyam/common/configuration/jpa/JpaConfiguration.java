package com.wandted.matitnyam.common.configuration.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 민경수
 * @description jpa configuration
 * @since 2023.11.02
 **********************************************************************************************************************/
@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"com.wandted.matitnyam.domain.entity"})
@EnableJpaRepositories(basePackages = {"com.wandted.matitnyam.repository"})
public class JpaConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}