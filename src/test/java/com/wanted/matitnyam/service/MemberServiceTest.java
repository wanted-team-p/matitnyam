package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Coordinates;
import com.wanted.matitnyam.domain.Member;
import com.wanted.matitnyam.dto.MemberDetails;
import com.wanted.matitnyam.dto.MemberRequest;
import com.wanted.matitnyam.dto.TokenResponse;
import com.wanted.matitnyam.repository.MemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Rollback
    @DisplayName("set 중복 요청 테스트")
    @Test
    void duplicatedMemberTest() {
        String username = "kim-jeonghyun";
        String password = "1234";
        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(password)
                .build();

        memberService.set(memberRequest);

        Assertions
                .assertThatThrownBy(() -> memberService.set(memberRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Rollback
    @DisplayName("signIn 테스트")
    @Test
    void signInTest() {
        String username = "kim-jeonghyun";
        String password = "1234";
        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(password)
                .build();

        memberService.set(memberRequest);

        TokenResponse response = memberService.signIn(memberRequest);
        System.out.println(response.token());
    }

    @Rollback
    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        String username = "kim-jeonghyun";
        String password = "1234";
        Double latitude = 3.14;
        Double longitude = 1.592;

        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(password)
                .build();
        memberService.set(memberRequest);

        Coordinates coordinates = Coordinates.builder()
                .latitudeInDegrees(latitude)
                .longitudeInDegrees(longitude)
                .build();
        memberService.updateCoordinates(coordinates, username);

        Optional<Member> mayBeFoundMember = memberRepository.findByUsername(username);
        Assertions
                .assertThat(mayBeFoundMember.isPresent())
                .isTrue();

        Member foundMember = mayBeFoundMember.get();
        Assertions
                .assertThat(foundMember.getLatitude())
                .isEqualTo(latitude);
        Assertions
                .assertThat(foundMember.getLongitude())
                .isEqualTo(longitude);
        Assertions
                .assertThat(memberRepository.findAll().size())
                .isEqualTo(1);
    }

    @Rollback
    @DisplayName("getDetails 테스트")
    @Test
    void getDetailsTest() {
        String username = "kim-jeonghyun";
        String password = "1234";
        Double latitude = 3.14;
        Double longitude = 1.592;

        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(password)
                .build();
        memberService.set(memberRequest);

        Coordinates coordinates = Coordinates.builder()
                .latitudeInDegrees(latitude)
                .longitudeInDegrees(longitude)
                .build();
        memberService.updateCoordinates(coordinates, username);

        Optional<MemberDetails> mayBeFoundMemberDetails = memberRepository.findDetails(username);
        Assertions
                .assertThat(mayBeFoundMemberDetails.isPresent())
                .isTrue();

        MemberDetails foundMemberDetails = mayBeFoundMemberDetails.get();
        Assertions
                .assertThat(foundMemberDetails.latitude())
                .isEqualTo(latitude);
        Assertions
                .assertThat(foundMemberDetails.longitude())
                .isEqualTo(longitude);
        Assertions
                .assertThat(memberRepository.findAll().size())
                .isEqualTo(1);
    }

}