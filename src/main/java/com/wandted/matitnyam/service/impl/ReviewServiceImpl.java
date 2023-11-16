package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.vo.ReviewVo;
import com.wandted.matitnyam.repository.ReviewRepository;
import com.wandted.matitnyam.service.RestaurantService;
import com.wandted.matitnyam.service.ReviewService;
import com.wandted.matitnyam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 민경수
 * @description review service impl
 * @since 2023.11.02
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Transactional
    @Override
    public void set(ReviewVo.Create create) {
        if (isWritable(create.getUserSeq(), create.getRestaurantSeq())) {
            repository.save(
                    create.toEntity(
                            userService.get(create.getUserSeq()),
                            restaurantService.get(create.getRestaurantSeq()
                            )
                    )
            );
        }
    }

    private boolean isWritable(Long userSeq, Long restaurantSeq) {
        if (repository.findBy(userSeq, restaurantSeq).isPresent()) {
            throw new IllegalArgumentException("이미 후기를 작성한 식당입니다.");
        }

        return true;
    }

}