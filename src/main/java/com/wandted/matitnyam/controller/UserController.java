package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.vo.UserVo;
import com.wandted.matitnyam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 민경수
 * @description user controller
 * @since 2023.11.02
 **********************************************************************************************************************/
@Slf4j
@CrossOrigin(origins = "*")
@RestController("UserController")
@RequiredArgsConstructor
@RequestMapping(value = UserController.RESOURCE_URI)
public class UserController {

    public static final String RESOURCE_URI = "/users";

    private final UserService service;

    /**
     * 회원가입
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(UserVo.Create create) {
        service.set(create);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 로그인
     */
    @PostMapping(path = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signIn(UserVo.SignIn signIn) {
        service.signIn(signIn);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 정보조회
     */
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> read(UserVo.Read read) {
        service.get(read);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 정보 업데이트
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(UserVo.Update update) {
        service.modify(update);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}