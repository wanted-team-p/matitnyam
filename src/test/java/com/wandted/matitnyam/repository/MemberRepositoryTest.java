package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Authority;
import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.CoordinatesRequest;
import com.wandted.matitnyam.dto.MemberDetails;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.PrincipalDto;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private final String username = "kim-jeonghyun";
    private final String password = "1234";

    @Test
    void hasDuplicatedNameTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);
        Assertions.assertThat(memberRepository.hasDuplicatedName(username)).isTrue();
    }

    @Test
    void findIdByUsernameAndPasswordTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);
        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(password)
                .build();
        Optional<PrincipalDto> mayBeFoundPrincipal = memberRepository.findByNameAndPassword(memberRequest);
        Assertions.assertThat(mayBeFoundPrincipal.isPresent()).isTrue();
        Assertions.assertThat(mayBeFoundPrincipal.get().name()).isEqualTo(username);
    }

    @Test
    void findIdByNameAndPasswordExceptionTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);
        String wrongPassword = "1235";
        MemberRequest memberRequest = MemberRequest.builder()
                .name(username)
                .password(wrongPassword)
                .build();
        Optional<PrincipalDto> mayBeFoundMember = memberRepository.findByNameAndPassword(memberRequest);
        Assertions.assertThat(mayBeFoundMember.isEmpty()).isTrue();
    }

    @Test
    void findByUsernameTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);
        Optional<Member> mayBeFoundMember = memberRepository.findByUsername(username);
        Assertions.assertThat(mayBeFoundMember.isPresent()).isTrue();
        Member foundMember = mayBeFoundMember.get();
    }

    @Test
    void findDetailsTest() {
        Double latitude = 3.14;
        Double longitude = 1.592;
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        CoordinatesRequest coordinatesRequest = CoordinatesRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
        member.setCoordinates(coordinatesRequest);
        memberRepository.save(member);

        Optional<MemberDetails> mayBeFoundMemberDetails = memberRepository.findDetails(username);
        Assertions.assertThat(mayBeFoundMemberDetails.isPresent()).isTrue();
        MemberDetails foundMemberDetails = mayBeFoundMemberDetails.get();

        Assertions.assertThat(foundMemberDetails.latitude()).isEqualTo(latitude);
        Assertions.assertThat(foundMemberDetails.longitude()).isEqualTo(longitude);
        Assertions.assertThat(foundMemberDetails.name()).isEqualTo(username);
        Assertions.assertThat(foundMemberDetails.authority()).isEqualTo(Authority.USER);
    }

}