package com.wandted.matitnyam.domain;

import lombok.Builder;

public record Region(String dosi, String sgg, String lon, String lat) {

    @Builder
    public Region {
    }

}
