package com.wanted.matitnyam.dto;

import com.wanted.matitnyam.domain.Authority;
import lombok.Builder;

public record MemberDetailResponse(Long seq, String name, Authority authority, Double latitude, Double longitude) {

    @Builder
    public MemberDetailResponse {
    }

}
