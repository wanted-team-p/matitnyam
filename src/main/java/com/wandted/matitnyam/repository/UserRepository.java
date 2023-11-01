package com.wandted.matitnyam.repository;

import com.wandted.matitnyam.domain.entity.User;
import com.wandted.matitnyam.repository.querydsl.UserRepositoryQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueryDsl {
}
