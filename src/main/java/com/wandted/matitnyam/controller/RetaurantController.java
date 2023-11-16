package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 민경수
 * @description retaurant controller
 * @since 2023.11.02
 **********************************************************************************************************************/
@Slf4j
@CrossOrigin(origins = "*")
@RestController("RetaurantController")
@RequiredArgsConstructor
@RequestMapping(value = RetaurantController.RESOURCE_URI)
public class RetaurantController {

    private final RestaurantService service;

    public static final String RESOURCE_URI = "/restaurants";

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> read() {
        service.get();

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> read(@PathVariable("seq") Long seq) {
        service.get(seq);

        return ResponseEntity.ok().build();
    }

}