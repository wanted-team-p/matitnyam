package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Coordinates;
import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.MemberDetails;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.MemberResponse;
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

    public MemberDetails getDetails(PrincipalDto principal) {
        Optional<MemberDetails> mayBeFoundMemberDetails = memberRepository.findDetails(principal.name());
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
