package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.vo.RegionVo;
import com.wandted.matitnyam.domain.vo.RegionVo.Response;
import com.wandted.matitnyam.service.RegionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<Response>> read(@ModelAttribute RegionVo.Search search) {
        return ResponseEntity.ok(service.get(search).entrySet().stream().map(e -> RegionVo.Response.toVo(e)).toList());
    }

}