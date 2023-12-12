package com.wandted.matitnyam.util;

import java.io.IOException;
import java.util.List;
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
        dosiSggFinder.init();
        String sgg = "강릉시";
        List<String> dosiList = dosiSggFinder.findDosiList(sgg);
        assert dosiList != null;
        for (String dosi : dosiList) {
            System.out.println(dosi + ' ');
        }
    }

    @DisplayName("시도 정보로 시군구 리스트 조회 테스트")
    @Test
    void findSggListTest() throws IOException {
        dosiSggFinder.init();
        String dosi = "강원";
        List<String> sggList = dosiSggFinder.findSggList(dosi);
        assert sggList != null;
        for (String sgg : sggList) {
            System.out.print(sgg + ' ');
        }
    }

}