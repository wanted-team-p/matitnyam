package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.vo.UserVo;
import com.wandted.matitnyam.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 민경수
 * @description review controller
 * @since 2023.11.02
 **********************************************************************************************************************/
@Slf4j
@CrossOrigin(origins = "*")
@RestController("ReviewController")
@RequiredArgsConstructor
@RequestMapping(value = ReviewController.RESOURCE_URI)
public class ReviewController {

    public static final String RESOURCE_URI = "/restaurants";

    private final ReviewService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(UserVo.Create create) {
        service.set(create);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}