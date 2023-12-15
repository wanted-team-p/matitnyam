package com.wanted.matitnyam.controller;

import com.wanted.matitnyam.domain.Region;
import com.wanted.matitnyam.dto.Principal;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.service.RegionService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/find")
    ResponseEntity<List<String>> find(@ModelAttribute Region region, @Principal PrincipalDto principal)
            throws IOException {
        if (region.sgg() == null && region.dosi() == null) {
            throw new IllegalArgumentException("시군구 또는 시도 정보를 입력해주세요.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regionService.find(region));
    }

}
