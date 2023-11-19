package com.wandted.matitnyam.user.repository;

import com.wandted.matitnyam.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
}
