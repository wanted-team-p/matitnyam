package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 민경수
 * @description region controller
 * @since 2023.11.02
 **********************************************************************************************************************/
@Slf4j
@CrossOrigin(origins = "*")
@RestController("RegionController")
@RequiredArgsConstructor
@RequestMapping(value = RegionController.RESOURCE_URI)
public class RegionController {

    private final RegionService service;

    public static final String RESOURCE_URI = "/regions";

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> read() {
        service.get();

        return ResponseEntity.ok().build();
    }

}