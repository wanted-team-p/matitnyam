package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Coordinates;
import com.wanted.matitnyam.domain.Member;
import com.wanted.matitnyam.dto.MemberDetailResponse;
import com.wanted.matitnyam.dto.MemberRequest;
import com.wanted.matitnyam.dto.MemberResponse;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.TokenResponse;
import com.wanted.matitnyam.exception.ResourceNotFoundException;
import com.wanted.matitnyam.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final AuthTokenService authTokenService;

    @Transactional
    public MemberResponse set(MemberRequest memberRequest) {
        // TODO: memberRequest의 비밀번호 유효성 검증 로직
        checkDuplicatedName(memberRequest.name());

        Member member = Member.builder()
                .name(memberRequest.name())
                .password(memberRequest.password())
                .build();
        Member createdMember = memberRepository.save(member);
        return createdMember.toResponse();
    }

    public TokenResponse signIn(MemberRequest memberRequest) {
        Optional<PrincipalDto> mayBeFoundPrincipal = memberRepository.findPrincipalByNameAndPassword(memberRequest);
        if (mayBeFoundPrincipal.isEmpty()) {
            throw new ResourceNotFoundException("잘못된 계정 또는 잘못된 비밀번호입니다.");
        }
        return TokenResponse.builder()
                .token(authTokenService.createToken(mayBeFoundPrincipal.get()))
                .build();
    }

    @Transactional
    public TokenResponse updateCoordinates(Coordinates coordinates, String username) {
        Optional<Member> mayBeFoundMember  = memberRepository.findByUsername(username);
        if (mayBeFoundMember.isEmpty()) {
            throw new ResourceNotFoundException("유저 정보를 찾을 수 없습니다.");
        }
        Member foundMember = mayBeFoundMember.get();
        foundMember.setCoordinates(coordinates);
        Member updatedMember = memberRepository.save(foundMember);

        return TokenResponse.builder()
                .token(authTokenService.createToken(updatedMember.toPrincipalDto()))
                .build();
    }

    public MemberDetailResponse getDetail(PrincipalDto principal) {
        Optional<MemberDetailResponse> mayBeFoundMemberDetails = memberRepository.findDetail(principal.name());
        if (mayBeFoundMemberDetails.isEmpty()) {
            throw new ResourceNotFoundException("유저 정보를 찾을 수 없습니다.");
        }
        return mayBeFoundMemberDetails.get();
    }

    private void checkDuplicatedName(String username) {
        boolean hasDuplicatedName = memberRepository.hasDuplicatedName(username);
        if (hasDuplicatedName) {
            throw new IllegalArgumentException("동일한 이름의 사용자가 존재합니다.");
        }
    }

}
