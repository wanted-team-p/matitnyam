package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.Region;
import com.wandted.matitnyam.dto.Principal;
import com.wandted.matitnyam.dto.PrincipalDto;
import com.wandted.matitnyam.dto.RestaurantDetailDto;
import com.wandted.matitnyam.dto.RestaurantDto;
import com.wandted.matitnyam.dto.RestaurantRequest;
import com.wandted.matitnyam.service.RestaurantService;
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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/restaurants")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/find-region")
    List<String> findRegion(@ModelAttribute Region region,
                            @Principal PrincipalDto principal) throws IOException {
        if (region.getSgg() == null && region.getDosi() == null) {
            throw new IllegalArgumentException("시군구 또는 시도 정보를 입력해주세요.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.findRegion(region))
                .getBody();
    }

    @GetMapping("/search")
    List<RestaurantDto> search(@ModelAttribute @Valid RestaurantRequest restaurantRequest,
                               @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.search(restaurantRequest))
                .getBody();
    }

    @GetMapping("/detail/{id}")
    RestaurantDetailDto getDetail(@PathVariable Long id, @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getDetailById(id))
                .getBody();
    }

}
