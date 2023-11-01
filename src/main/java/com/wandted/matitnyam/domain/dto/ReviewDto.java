package com.wandted.matitnyam.domain.dto;

import java.io.Serial;

/**
 * @author 민경수
 * @description review dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class ReviewDto extends AbstractDto{
    @Serial
    private static final long serialVersionUID = 2381396295295088569L;


    private Integer rating;
    private String content;

    public ReviewDto(Integer rating, String content) {
        this.rating = rating;
        this.content = content;
    }

}