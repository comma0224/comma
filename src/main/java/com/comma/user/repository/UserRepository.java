package com.comma.user.repository;

import com.comma.user.domain.User;
import com.comma.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    User findByUserId(String userId);

    @Query("SELECT new com.comma.user.domain.Users(u.userKey, u.level, u.exp, l.requireExp, u.cash, u.point, u.nickname) " +
            "FROM User u " +
            "LEFT JOIN Level l ON u.level = l.level " +
            "WHERE u.userKey = :userKey")
    Users findUsersByUserKey(Long userKey);

}
