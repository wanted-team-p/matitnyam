package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Region;
import com.wanted.matitnyam.util.DosiSggFinder;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final DosiSggFinder dosiSggFinder;

    public List<String> find(Region region) throws IOException {
        if (region.sgg() != null) {
            return dosiSggFinder.findDosiList(region.sgg());
        }
        return dosiSggFinder.findSggList(region.dosi());
    }

}
