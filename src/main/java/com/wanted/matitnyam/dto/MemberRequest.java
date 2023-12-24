package com.wanted.matitnyam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record MemberRequest(@NotBlank String name, @NotBlank String password) {

    @Builder
    public MemberRequest {
    }

}
