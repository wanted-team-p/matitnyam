package com.wanted.matitnyam.controller;

import com.wanted.matitnyam.domain.Region;
import com.wanted.matitnyam.dto.Principal;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.RegionRequest;
import com.wanted.matitnyam.dto.RestaurantDetailDto;
import com.wanted.matitnyam.dto.RestaurantDto;
import com.wanted.matitnyam.dto.RestaurantRequest;
import com.wanted.matitnyam.service.RestaurantService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/find-region")
    ResponseEntity<List<String>> findRegion(@ModelAttribute Region region,
                                            @Principal PrincipalDto principal) throws IOException {
        if (region.sgg() == null && region.dosi() == null) {
            throw new IllegalArgumentException("시군구 또는 시도 정보를 입력해주세요.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.findRegion(region));
    }

    @GetMapping("/search")
    ResponseEntity<List<RestaurantDto>> search(@ModelAttribute @Valid RestaurantRequest restaurantRequest,
                                               @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.search(restaurantRequest));
    }

    @GetMapping("/my-location-based/search")
    ResponseEntity<List<RestaurantDto>> myLocationBasedSearch(@RequestParam(name = "mobility") String mobilityAsString,
                                                              @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.myLocationBasedSearch(mobilityAsString, principal));
    }

    @GetMapping("/region-name-based/search")
    ResponseEntity<List<RestaurantDto>> regionNameBasedSearch(@ModelAttribute RegionRequest regionRequest,
                                                              @Principal PrincipalDto principal) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.regionNameBasedSearch(regionRequest));
    }

    @GetMapping("/detail/{id}")
    ResponseEntity<RestaurantDetailDto> getDetail(@PathVariable Long id, @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getDetailById(id));
    }

}
