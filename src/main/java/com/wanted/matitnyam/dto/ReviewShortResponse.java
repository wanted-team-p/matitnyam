package com.wanted.matitnyam.dto;

import lombok.Builder;

public record ReviewShortResponse(String username, Integer rating, String opinionInShort) {

    public static final int lengthLimit = 30;

    @Builder
    public ReviewShortResponse {
    }

}
