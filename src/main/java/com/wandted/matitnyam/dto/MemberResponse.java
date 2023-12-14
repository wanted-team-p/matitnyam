package com.wandted.matitnyam.dto;

import com.wandted.matitnyam.domain.Authority;
import lombok.Builder;

public record MemberResponse(String name, Authority authority, Double latitude, Double longitude) {

    @Builder
    public MemberResponse {
    }

}
