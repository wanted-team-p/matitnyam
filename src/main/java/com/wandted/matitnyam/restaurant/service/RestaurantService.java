package com.wandted.matitnyam.restaurant.service;

import com.wandted.matitnyam.common.DataException.NoDataException;
import com.wandted.matitnyam.restaurant.domain.Restaurant;
import com.wandted.matitnyam.restaurant.domain.RestaurantSepcification;
import com.wandted.matitnyam.restaurant.dto.RestaurantDetailResponse;
import com.wandted.matitnyam.restaurant.dto.RestaurantListResponse;
import com.wandted.matitnyam.restaurant.dto.RestaurantRequestParam;
import com.wandted.matitnyam.restaurant.repository.RestaurantRepository;
import com.wandted.matitnyam.util.MethodNameUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    //식당 상세 조회
    public RestaurantDetailResponse getRestaurantDetail(long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NoDataException(Long.toString(id))).toDetailResponse();
    }

    //식당 목록 조회
    public List<RestaurantListResponse> getRestaurantList(RestaurantRequestParam restaurantRequestParam){
         List<Restaurant> restaurants = restaurantRepository.findAll(
                RestaurantSepcification.isNearby(
                        restaurantRequestParam.lat(),
                        restaurantRequestParam.lon(),
                        restaurantRequestParam.range()),
                        Sort.by(restaurantRequestParam.orderDirection().equalsIgnoreCase("desc")? Direction.DESC : Direction.ASC,
                                restaurantRequestParam.orderBy()));

         //결과가 없는 경우 예외처리
         if(restaurants.isEmpty()){
             throw new NoDataException(MethodNameUtil.getMethodName());
         }

         return restaurants.stream()
                 .map(Restaurant::toListResponse)
                 .collect(Collectors.toList());
    }
}
