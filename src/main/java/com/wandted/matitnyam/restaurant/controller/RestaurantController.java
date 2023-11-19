package com.wandted.matitnyam.restaurant.controller;

import com.wandted.matitnyam.restaurant.dto.RestaurantDetailResponse;
import com.wandted.matitnyam.restaurant.dto.RestaurantListResponse;
import com.wandted.matitnyam.restaurant.dto.RestaurantRequestParam;
import com.wandted.matitnyam.restaurant.service.RestaurantService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailResponse> getRestaurantDetail(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurantService.getRestaurantDetail(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<RestaurantListResponse>> getRestaurantList(@ModelAttribute RestaurantRequestParam restaurantRequestParam){
        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurantService.getRestaurantList(restaurantRequestParam));
    }
}
