package com.wanted.matitnyam.domain;

import lombok.Builder;

public record Region(String dosi, String sgg, Double lon, Double lat) {

    @Builder
    public Region {
    }

}
