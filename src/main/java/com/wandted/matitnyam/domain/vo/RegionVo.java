package com.wandted.matitnyam.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.wandted.matitnyam.domain.entity.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 민경수
 * @description region vo
 * @since 2023.11.03
 **********************************************************************************************************************/
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class RegionVo {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {

        public static Response toVo(Region region) {
            return null;
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Data {

        @CsvBindByName(column = "city")
        private String city;

        @CsvBindByName(column = "district")
        private String district;

        @CsvBindByName(column = "longitude")
        private Double longitude;

        @CsvBindByName(column = "latitude")
        private Double latitude;

        public Data(String city, String district, Double longitude, Double latitude) {
            this.city = city;
            this.district = district;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public Region toEntity() {
            return Region.builder()
                    .city(city)
                    .district(district)
                    .longitude(longitude)
                    .latitude(latitude)
                    .build();
        }

        public void validateData() {
            if (city == null || district == null || longitude == null || latitude == null) {
                throw new IllegalArgumentException();
            }
        }

    }

}