package com.wandted.matitnyam.dto;

import com.wandted.matitnyam.domain.Authority;
import lombok.Builder;

public record PrincipalDto(String name, Authority authority) {

    @Builder
    public PrincipalDto {
    }

}
