package com.wandted.matitnyam.util;

import com.wandted.matitnyam.domain.Coordinates;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DosiSggFinderTest {

    @Autowired
    private DosiSggFinder dosiSggFinder;

    @DisplayName("시군구 정보로 시도 리스트 조회 테스트")
    @Test
    void findDosiListTest() throws IOException {
        String sgg = "강릉시";
        List<String> dosiList = dosiSggFinder.findDosiList(sgg);
        assert dosiList != null;
        for (String dosi : dosiList) {
            System.out.println(dosi + ' ');
        }
    }

    @DisplayName("시/도 정보로 시군구 리스트 조회 테스트")
    @Test
    void findSggListTest() throws IOException {
        String dosi = "강원";
        List<String> sggList = dosiSggFinder.findSggList(dosi);
        assert sggList != null;
        for (String sgg : sggList) {
            System.out.print(sgg + ' ');
        }
    }

    @DisplayName("시/도, 시군구 정보로 지역 중심부 위도/경도 조회 테스트")
    @Test
    void findCoordinatesByDosiAndSggTest() throws IOException {
        String dosi = "강원";
        String sgg = "강릉시";
        Double latitude = 37.74913611;
        Double longitude = 128.8784972;

        Coordinates coordinates = dosiSggFinder.findCoordinatesByDosiAndSgg(dosi, sgg);

        Assertions
                .assertThat(coordinates.latitudeInDegrees())
                .isEqualTo(latitude);
        Assertions
                .assertThat(coordinates.longitudeInDegrees())
                .isEqualTo(longitude);
    }
}