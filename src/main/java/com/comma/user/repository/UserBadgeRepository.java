package com.comma.user.repository;

import com.comma.user.domain.UserBadge;
import com.comma.user.domain.UserBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {

    List<UserBadge> findByUserKey(Long userKey);

    @Query("SELECT new com.comma.user.domain.UserBadges(ub.userBadgeKey, ub.badgeKey, ub.userKey, b.name, b.file, b.tier) " +
            "FROM UserBadge ub " +
            "LEFT JOIN Badge b ON ub.badgeKey = b.badgeKey " +
            "WHERE ub.userKey = :userKey " +
            "ORDER BY ub.createdAt DESC")
    List<UserBadges> findUserBadgesByUserKey(Long userKey);
}

