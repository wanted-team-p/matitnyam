package com.wanted.matitnyam.repository;

import com.wanted.matitnyam.domain.Authority;
import com.wanted.matitnyam.domain.Coordinates;
import com.wanted.matitnyam.domain.Member;
import com.wanted.matitnyam.dto.MemberDetailResponse;
import com.wanted.matitnyam.dto.MemberRequest;
import com.wanted.matitnyam.dto.PrincipalDto;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    private static final String username = "kim-jeonghyun";

    private static final String password = "1234";

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("동일한 유저 이름이 있는 경우에 대한 테스트")
    @Test
    void hasDuplicatedNameTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);

        Assertions
                .assertThat(memberRepository.hasDuplicatedName(username))
                .isTrue();
    }

    @DisplayName("유저 이름과 패스워드로 유저를 찾는 메소드 테스트")
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
        Optional<PrincipalDto> mayBeFoundPrincipal = memberRepository.findPrincipalByNameAndPassword(memberRequest);

        Assertions
                .assertThat(mayBeFoundPrincipal.isPresent())
                .isTrue();
        Assertions
                .assertThat(mayBeFoundPrincipal.get().name())
                .isEqualTo(username);
    }

    @DisplayName("유저 이름과 패스워드로 유저를 찾는 메소드 예외 테스트")
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

        Optional<PrincipalDto> mayBeFoundMember = memberRepository.findPrincipalByNameAndPassword(memberRequest);
        Assertions
                .assertThat(mayBeFoundMember.isEmpty())
                .isTrue();
    }

    @DisplayName("유저 이름으로 해당 유저를 찾는 메소드 테스트")
    @Test
    void findByUsernameTest() {
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        memberRepository.save(member);

        Optional<Member> mayBeFoundMember = memberRepository.findByUsername(username);

        Assertions
                .assertThat(mayBeFoundMember.isPresent())
                .isTrue();
    }

    @DisplayName("유저 상세 정보 조회 메소드 테스트")
    @Test
    void findDetailsTest() {
        Double latitude = 3.14;
        Double longitude = 1.592;
        Member member = Member.builder()
                .name(username)
                .password(password)
                .build();
        Coordinates coordinates = Coordinates.builder()
                .latitudeInDegrees(latitude)
                .longitudeInDegrees(longitude)
                .build();
        member.setCoordinates(coordinates);
        Member createdMember = memberRepository.save(member);

        Optional<MemberDetailResponse> mayBeFoundMemberDetails = memberRepository.findDetail(username);
        Assertions
                .assertThat(mayBeFoundMemberDetails.isPresent())
                .isTrue();

        MemberDetailResponse foundMemberDetailResponse = mayBeFoundMemberDetails.get();
        Assertions
                .assertThat(foundMemberDetailResponse.seq())
                .isEqualTo(createdMember.getSeq());
        Assertions
                .assertThat(foundMemberDetailResponse.latitude())
                .isEqualTo(latitude);
        Assertions
                .assertThat(foundMemberDetailResponse.longitude())
                .isEqualTo(longitude);
        Assertions
                .assertThat(foundMemberDetailResponse.name())
                .isEqualTo(username);
        Assertions
                .assertThat(foundMemberDetailResponse.authority())
                .isEqualTo(Authority.USER);
    }

}