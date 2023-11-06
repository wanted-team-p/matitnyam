package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping(path = "/")
    public ResponseEntity<Member> createMember(@Valid @ModelAttribute Member member) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.set(member));
    }

}
