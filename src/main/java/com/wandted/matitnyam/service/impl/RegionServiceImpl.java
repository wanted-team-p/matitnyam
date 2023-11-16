package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.domain.vo.RegionVo.Search;
import com.wandted.matitnyam.repository.RegionRepository;
import com.wandted.matitnyam.service.RegionService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 민경수
 * @description region service impl
 * @since 2023.11.02
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;

    @Override
    public Map<String, List<String>> get(Search search) {
        List<Region> regions = repository.findAllBy(search.getCity(), search.getDistrict());

        return toCityDistricts(regions);

    }

    @Override
    public void set(Region region) {
        repository.save(region);
    }

    private Map<String, List<String>> toCityDistricts(List<Region> searchResults) {
        return searchResults.stream()
                .collect(Collectors.groupingBy(Region::getCity,
                        Collectors.mapping(Region::getDistrict, Collectors.toList())));
    }
}