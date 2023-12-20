package com.wanted.matitnyam.controller;

import com.wanted.matitnyam.domain.Coordinates;
import com.wanted.matitnyam.dto.MemberDetailResponse;
import com.wanted.matitnyam.dto.MemberRequest;
import com.wanted.matitnyam.dto.MemberResponse;
import com.wanted.matitnyam.dto.Principal;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.TokenResponse;
import com.wanted.matitnyam.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping(path = "/")
    public ResponseEntity<MemberResponse> signUp(@Valid @ModelAttribute MemberRequest memberRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.set(memberRequest));
    }

    @GetMapping(path = "/")
    public ResponseEntity<TokenResponse> signIn(@Valid @ModelAttribute MemberRequest memberRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.signIn(memberRequest));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<TokenResponse> update(@Valid @ModelAttribute Coordinates coordinates,
                                                @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.updateCoordinates(coordinates, principal.name()));
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<MemberDetailResponse> getDetail(@Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.getDetail(principal));
    }

}
