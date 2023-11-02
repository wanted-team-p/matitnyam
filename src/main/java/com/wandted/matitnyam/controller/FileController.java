package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.vo.FileVo;
import com.wandted.matitnyam.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 민경수
 * @description file controller
 * @since 2023.11.02
 **********************************************************************************************************************/
@Slf4j
@RestController
@RequestMapping(FileController.RESOURCE_URI)
@RequiredArgsConstructor
public class FileController {

    static final String RESOURCE_URI = "/files";

    private final FileService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFile(@Validated @ModelAttribute FileVo.Create create) {
        service.set(create);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}