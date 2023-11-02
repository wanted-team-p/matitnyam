package com.wandted.matitnyam.domain.vo;

import com.wandted.matitnyam.domain.entity.Restaurant;
import com.wandted.matitnyam.domain.entity.Review;
import com.wandted.matitnyam.domain.entity.User;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description review vo
 * @since 2023.11.02
 **********************************************************************************************************************/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 3535352555358877731L;


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Create {
        private Long restaurantSeq;
        private Long userSeq;
        private Integer rating;
        private String content;

        public Review toEntity(User user, Restaurant restaurant) {
            return Review.builder().content(content).rating(rating).user(user).restaurant(restaurant).build();
        }
    }

}