package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Member;
import com.wandted.matitnyam.dto.MemberDetails;
import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.PrincipalDto;
import java.util.Optional;

public interface MemberCustomRepository {

    boolean hasDuplicatedName(String username);

    Optional<PrincipalDto> findByNameAndPassword(MemberRequest memberRequest);

    Optional<Member> findByUsername(String username);

    Optional<MemberDetails> findDetails(String username);

}
