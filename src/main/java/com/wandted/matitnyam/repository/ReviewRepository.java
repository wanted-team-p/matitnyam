package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.entity.Review;
import com.wandted.matitnyam.repository.querydsl.ReviewRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryQueryDsl {
}
