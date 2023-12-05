package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.CoordinatesRequest;
import com.wandted.matitnyam.dto.MemberDetails;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.Principal;
import com.wandted.matitnyam.dto.PrincipalDto;
import com.wandted.matitnyam.dto.TokenResponse;
import com.wandted.matitnyam.service.MemberService;
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
    public ResponseEntity<Member> signUp(@Valid @ModelAttribute MemberRequest memberRequest) {
        memberService.set(memberRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(path = "/")
    public ResponseEntity<TokenResponse> signIn(@Valid @ModelAttribute MemberRequest memberRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.signIn(memberRequest));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> update(@Valid @ModelAttribute CoordinatesRequest coordinatesRequest,
                                       @Principal PrincipalDto principal) {
        memberService.update(coordinatesRequest, principal.name());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping(path = "/details")
    public ResponseEntity<MemberDetails> getDetails(@Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberService.getDetails(principal));
    }

}
