package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
}
