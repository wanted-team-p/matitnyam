package com.wanted.matitnyam.dto;

import lombok.Builder;

public record ReviewDto(String username, Integer rating, String opinionInShort) {

    public static final int lengthLimit = 30;

    @Builder
    public ReviewDto {
    }

}
