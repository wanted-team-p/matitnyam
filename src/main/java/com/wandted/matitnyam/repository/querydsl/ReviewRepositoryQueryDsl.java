package com.wandted.matitnyam.repository.querydsl;

import com.wandted.matitnyam.domain.entity.Review;
import java.util.Optional;

public interface ReviewRepositoryQueryDsl {

    Optional<Review> findBy(Long userSeq, Long restaurantSeq);
}
