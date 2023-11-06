package com.wandted.matitnyam.service;

import com.sun.jdi.request.DuplicateRequestException;
import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member set(Member member) {
        Long mayBeFoundUserId = memberRepository.findIdByName(member.getName());
        if (mayBeFoundUserId != null) {
            throw new DuplicateRequestException("동일한 이름의 사용자가 존재합니다.");
        }
        return memberRepository.save(member);
    }

}
