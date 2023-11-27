package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.PrincipalDto;
import com.wandted.matitnyam.dto.TokenResponse;
import com.wandted.matitnyam.exception.ResourceNotFoundException;
import com.wandted.matitnyam.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthTokenService authTokenService;

    public Member set(MemberRequest memberRequest) {
        // TODO: memberRequest의 유효한 비밀번호 검증 로직
        checkDuplicatedName(memberRequest.name());

        Member newMember = Member.builder()
                .name(memberRequest.name())
                .password(memberRequest.password())
                .build();
        return memberRepository.save(newMember);
    }

    public TokenResponse signIn(MemberRequest memberRequest) {
        Optional<PrincipalDto> mayBeFoundPrincipal = memberRepository.findByNameAndPassword(memberRequest);
        if (mayBeFoundPrincipal.isEmpty()) {
            throw new ResourceNotFoundException("잘못된 계정 또는 잘못된 비밀번호입니다.");
        }
        return TokenResponse.builder()
                .token(authTokenService.createToken(mayBeFoundPrincipal.get()))
                .build();
    }

    private void checkDuplicatedName(String name) {
        boolean hasDuplicatedName = memberRepository.hasDuplicatedName(name);
        if (hasDuplicatedName) {
            throw new IllegalArgumentException("동일한 이름의 사용자가 존재합니다.");
        }
    }

}
