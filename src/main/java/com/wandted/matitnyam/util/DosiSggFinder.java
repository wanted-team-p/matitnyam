package com.wandted.matitnyam.util;

import com.wandted.matitnyam.domain.Coordinates;
import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.domain.csvparser.SggDataParser;
import com.wandted.matitnyam.exception.ResourceNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // 시/도 정보와 시군구 모두가 입력으로 주어질 것.
    private Map<String, List<Region>> dosiToRegion;

    public List<String> findSggList(String dosi) throws IOException {
        if (dosiToSgg == null) {
            initialize();
        }
        return dosiToSgg.get(dosi);
    }

    public List<String> findDosiList(String sgg) throws IOException {
        if (sggToDosi == null) {
            initialize();
        }
        return sggToDosi.get(sgg);
    }

    public Coordinates findCoordinatesByDosiAndSgg(String dosi, String sgg) throws IOException {
        if (dosiToRegion == null) {
            initialize();
        }
        List<Region> regionList = dosiToRegion.get(dosi);
        if (regionList.isEmpty()) {
            throw new ResourceNotFoundException("요청받은 시/도 정보에 대한 시군구 정보가 없습니다.");
        }
        for (Region region : regionList) {
            if (region.sgg().equals(sgg)) {
                return Coordinates.builder()
                        .latitudeInDegrees(region.lat())
                        .longitudeInDegrees(region.lon())
                        .build();
            }
        }
        throw new ResourceNotFoundException("요청받은 시군구에 대한 정보가 없습니다.");
    }

    private void initialize() throws IOException {
        dosiToSgg = new HashMap<>();
        sggToDosi = new HashMap<>();
        dosiToRegion = new HashMap<>();
        List<Region> regionList = sggDataParser.parse();
        for (Region region : regionList) {
            updateSggList(region);
            updateDosiList(region);
            updateDosiToRegion(region);
        }
    }

    private void updateSggList(Region region) {
        List<String> sggList = dosiToSgg.get(region.dosi());
        if (sggList == null) {
            sggList = new ArrayList<>();
        }
        sggList.add(region.sgg());
        dosiToSgg.put(region.dosi(), sggList);
    }

    private void updateDosiList(Region region) {
        List<String> dosiList = sggToDosi.get(region.sgg());
        if (dosiList == null) {
            dosiList = new ArrayList<>();
        }
        dosiList.add(region.dosi());
        sggToDosi.put(region.sgg(), dosiList);
    }

    private void updateDosiToRegion(Region region) {
        List<Region> regionList = dosiToRegion.get(region.dosi());
        if (regionList == null) {
            regionList = new ArrayList<>();
        }
        regionList.add(region);
        dosiToRegion.put(region.dosi(), regionList);
    }

}
