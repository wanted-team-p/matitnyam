package com.wanted.matitnyam.repository;

import com.wanted.matitnyam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
}
