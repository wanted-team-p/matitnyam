package com.wandted.matitnyam.dto;

import lombok.Builder;

public record TokenResponse(String token) {

    @Builder
    public TokenResponse{
    }

}
