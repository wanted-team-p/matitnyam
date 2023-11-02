package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.repository.RegionRepository;
import com.wandted.matitnyam.service.RegionService;
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
    public void get() {

    }

    @Override
    public void set(Region region) {
        repository.save(region);
    }

}