package com.wandted.matitnyam.domain.dto;

import java.io.Serial;

/**
 * @author 민경수
 * @description business type dto
 * @since 2023.11.01
 **********************************************************************************************************************/
public class BusinessTypeDto extends AbstractDto{
    @Serial
    private static final long serialVersionUID = -8944682556013645258L;

    private String name;

    public BusinessTypeDto(String name) {
        this.name = name;
    }

}