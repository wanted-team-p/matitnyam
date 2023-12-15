package com.wanted.matitnyam.dto;

import com.wanted.matitnyam.domain.Authority;
import lombok.Builder;

public record MemberResponse(String name, Authority authority, Double latitude, Double longitude) {

    @Builder
    public MemberResponse {
    }

}
