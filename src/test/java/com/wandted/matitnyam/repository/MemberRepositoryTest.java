package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findIdByNameTest() {
        String name = "kim-jeonghyun";
        Member member = new Member(name, "1234");
        memberRepository.save(member);
        Long foundUserId = memberRepository.findIdByName(name);
        Assertions.assertNotNull(foundUserId);
    }

}