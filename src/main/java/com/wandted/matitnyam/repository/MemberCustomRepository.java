package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.dto.MemberRequest;
import com.wandted.matitnyam.dto.PrincipalDto;
import java.util.Optional;

public interface MemberCustomRepository {

    boolean hasDuplicatedName(String userName);

    Optional<PrincipalDto> findByNameAndPassword(MemberRequest memberRequest);

}
