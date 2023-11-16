package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.entity.Restaurant;
import com.wandted.matitnyam.repository.RestaurantRepository;
import com.wandted.matitnyam.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 민경수
 * @description restaurant service impl
 * @since 2023.11.02
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public Restaurant get(Long seq) {
        return repository.findById(seq).orElseThrow(() -> new IllegalArgumentException("식당을 찾을 수 없습니다."));
    }

    @Override
    public void get() {

    }

}