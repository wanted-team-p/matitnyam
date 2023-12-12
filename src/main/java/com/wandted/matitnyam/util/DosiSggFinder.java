package com.wandted.matitnyam.util;

import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.domain.csvparser.SggDataParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DosiSggFinder {

    private final SggDataParser sggDataParser;

    // 시/도를 받고, 그 안에 속한 시군구 목록을 반환
    private Map<String, List<String>> dosiToSgg;

    // 시군구를 받고, 이를 포함하는 시/도 목록을 반환
    private Map<String, List<String>> sggToDosi;

    public void init() throws IOException {
        dosiToSgg = new HashMap<>();
        sggToDosi = new HashMap<>();
        List<Region> regionList = sggDataParser.parse();
        for (Region region : regionList) {
            updateSggList(region);
            updateDosiList(region);
        }
    }

    public List<String> findSggList(String dosi) throws IOException {
        if (dosiToSgg == null) {
            init();
        }
        return dosiToSgg.get(dosi);
    }

    public List<String> findDosiList(String sgg) throws IOException {
        if (sggToDosi == null) {
            init();
        }
        return sggToDosi.get(sgg);
    }

    private void updateSggList(Region region) {
        List<String> sggList = dosiToSgg.get(region.dosi());
        if (sggList == null) {
            sggList = new ArrayList<>();
        }
        Optional<String> mayBeFoundSgg = sggList.stream()
                .filter(sgg -> sgg.equals(region.sgg()))
                .findFirst();
        if (mayBeFoundSgg.isPresent()) {
            return;
        }
        sggList.add(region.sgg());
        dosiToSgg.put(region.dosi(), sggList);
    }

    private void updateDosiList(Region region) {
        List<String> dosiList = sggToDosi.get(region.sgg());
        if (dosiList == null) {
            dosiList = new ArrayList<>();
        }
        Optional<String> mayBeFoundDosi = dosiList.stream()
                .filter(dosi -> dosi.equals(region.dosi()))
                .findFirst();
        if (mayBeFoundDosi.isPresent()) {
            return;
        }
        dosiList.add(region.dosi());
        sggToDosi.put(region.sgg(), dosiList);
    }

}
