package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.entity.Restaurant;
import com.wandted.matitnyam.repository.querydsl.RestaurantRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueryDsl {
}
