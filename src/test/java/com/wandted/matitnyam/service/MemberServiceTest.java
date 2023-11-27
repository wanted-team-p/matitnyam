package com.wandted.matitnyam.service;

import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.TokenResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @DisplayName("set 중복 요청 테스트")
    @Test
    void duplicatedMemberTest() {
        MemberRequest memberRequest = MemberRequest.builder()
                .name("neppiness")
                .password("1234")
                .build();
        memberService.set(memberRequest);
        Assertions.assertThatThrownBy(() -> memberService.set(memberRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("signIn 테스트")
    @Test
    void signInTest() {
        MemberRequest memberRequest = MemberRequest.builder()
                .name("kim-jeonghyun")
                .password("1234")
                .build();
        memberService.set(memberRequest);
        TokenResponse response = memberService.signIn(memberRequest);
        System.out.println(response.token());
    }

}