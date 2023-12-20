package com.wanted.matitnyam.controller;

import com.wanted.matitnyam.dto.Principal;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.RegionRequest;
import com.wanted.matitnyam.dto.RestaurantDetailResponse;
import com.wanted.matitnyam.dto.RestaurantResponse;
import com.wanted.matitnyam.dto.RestaurantRequest;
import com.wanted.matitnyam.dto.ReviewShortResponse;
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

    @GetMapping("/search")
    ResponseEntity<List<RestaurantResponse>> search(@ModelAttribute @Valid RestaurantRequest restaurantRequest,
                                                    @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.search(restaurantRequest));
    }

    @GetMapping("/my-location-based/search")
    ResponseEntity<List<RestaurantResponse>> myLocationBasedSearch(@RequestParam(name = "mobility") String mobilityAsString,
                                                                   @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.myLocationBasedSearch(mobilityAsString, principal));
    }

    @GetMapping("/region-name-based/search")
    ResponseEntity<List<RestaurantResponse>> regionNameBasedSearch(@ModelAttribute RegionRequest regionRequest,
                                                                   @Principal PrincipalDto principal) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.regionNameBasedSearch(regionRequest));
    }

    @GetMapping("/detail/{id}")
    ResponseEntity<RestaurantDetailResponse> getDetail(@PathVariable Long id, @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getDetailById(id));
    }

    @GetMapping("/reviews-of/{id}")
    ResponseEntity<List<ReviewShortResponse>> getReviews(@PathVariable Long id, @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getReviewsById(id));
    }

}
