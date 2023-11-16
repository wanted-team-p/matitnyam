package com.wandted.matitnyam.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.wandted.matitnyam.domain.entity.Region;
import java.util.List;
import java.util.Map.Entry;
import lombok.Builder;
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

        private String city;
        private List<String> districts;

        @Builder
        public Response(String city, List<String> districts) {
            this.city = city;
            this.districts = districts;
        }

        public static Response toVo(Entry<String, List<String>> e) {
            return Response.builder().city(e.getKey()).districts(e.getValue()).build();
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
    public static class Search {
        private String city;
        private String district;

        public Search() {
            district = "";
            city = "";
        }

        @Override
        public String toString() {
            return city + " " + district;
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