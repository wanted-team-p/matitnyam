package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Member;
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

    private final String name = "kim-jeonghyun";
    private final String password = "1234";

    @Test
    void hasDuplicatedNameTest() {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .build();
        memberRepository.save(member);
        Assertions.assertThat(memberRepository.hasDuplicatedName(name)).isTrue();
    }

    @Test
    void findIdByNameAndPasswordTest() {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .build();
        memberRepository.save(member);
        MemberRequest memberRequest = MemberRequest.builder()
                .name(name)
                .password(password)
                .build();
        Optional<PrincipalDto> mayBeFoundPrincipal = memberRepository.findByNameAndPassword(memberRequest);
        Assertions.assertThat(mayBeFoundPrincipal.isPresent()).isTrue();
        Assertions.assertThat(mayBeFoundPrincipal.get().name()).isEqualTo(name);
    }

    @Test
    void findIdByNameAndPasswordExceptionTest() {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .build();
        memberRepository.save(member);
        String wrongPassword = "1235";
        MemberRequest memberRequest = MemberRequest.builder()
                .name(name)
                .password(wrongPassword)
                .build();
        Optional<PrincipalDto> mayBeFoundMember = memberRepository.findByNameAndPassword(memberRequest);
        Assertions.assertThat(mayBeFoundMember.isEmpty()).isTrue();
    }

}