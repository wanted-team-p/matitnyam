package com.wandted.matitnyam.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Region {

    String dosi;

    String sgg;

    String lon;

    String lat;

    @Builder
    public Region(final String dosi, final String sgg, final String lon, final String lat) {
        this.dosi = dosi;
        this.sgg = sgg;
        this.lon = lon;
        this.lat = lat;
    }

}
