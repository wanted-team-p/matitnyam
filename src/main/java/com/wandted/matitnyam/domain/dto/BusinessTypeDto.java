package com.wandted.matitnyam.domain.dto;

import java.io.Serial;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description business type dto
 * @since 2023.11.01
 **********************************************************************************************************************/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessTypeDto extends AbstractDto {
    @Serial
    private static final long serialVersionUID = -8944682556013645258L;

    private String name;

    public BusinessTypeDto(String name) {
        this.name = name;
    }

}