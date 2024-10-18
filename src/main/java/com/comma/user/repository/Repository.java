package com.comma.user.repository;

import com.comma.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
}
