package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Region;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @DisplayName("시도 정보를 통한 시군구 리스트 조회 테스트")
    @Test
    void findRegionByDosiTest() throws IOException {
        String gyeonggi = "경기";
        Region region = Region.builder()
                .dosi(gyeonggi)
                .build();
        List<String> sggList = regionService.find(region);
        for (String sgg : sggList) {
            System.out.print(sgg + ' ');
        }
    }

    @DisplayName("시군구 정보를 통한 시도 리스트 조회 테스트")
    @Test
    void findRegionBySggTest() throws IOException {
        String dongu = "동구";
        Region region = Region.builder()
                .sgg(dongu)
                .build();
        List<String> dosiList = regionService.find(region);
        for (String dosi : dosiList) {
            System.out.print(dosi + ' ');
        }
    }

}