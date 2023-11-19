package com.wandted.matitnyam.region.controller;

import com.wandted.matitnyam.region.dto.CityListResponse;
import com.wandted.matitnyam.region.dto.DistrictListResponse;
import com.wandted.matitnyam.region.service.RegionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("region")
@RestController
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    private ResponseEntity<List<CityListResponse>> getCityList(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(regionService.getCityList());
    }

    @GetMapping("/{city}")
    private ResponseEntity<List<DistrictListResponse>> getDistrictList(@PathVariable Long city){
        return ResponseEntity.status(HttpStatus.OK)
                .body(regionService.getDistrictList(city));
    }
}
