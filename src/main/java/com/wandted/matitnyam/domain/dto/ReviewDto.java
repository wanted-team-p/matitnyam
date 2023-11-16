package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description review dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto extends AbstractDto {
    @Serial
    private static final long serialVersionUID = 2381396295295088569L;


    private Integer rating;
    private String content;

    public ReviewDto(Integer rating, String content) {
        this.rating = rating;
        this.content = content;
    }

}