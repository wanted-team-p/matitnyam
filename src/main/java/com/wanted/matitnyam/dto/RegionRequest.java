package com.wanted.matitnyam.dto;

import lombok.Builder;

public record RegionRequest(String dosi, String sgg) {

    @Builder
    public RegionRequest {
    }

}
