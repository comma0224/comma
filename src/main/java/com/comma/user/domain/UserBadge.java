package com.comma.user.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_badge")
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_badge_key", nullable = false)
    private Long userBadgeKey;

    @Column(name = "badge_key", nullable = false)
    private Long badgeKey;

    @Column(name = "user_key", nullable = false)
    private Long userKey;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Getters and Setters

    public Long getUserBadgeKey() {
        return userBadgeKey;
    }

    public void setUserBadgeKey(Long userBadgeKey) {
        this.userBadgeKey = userBadgeKey;
    }

    public Long getBadgeKey() {
        return badgeKey;
    }

    public void setBadgeKey(Long badgeKey) {
        this.badgeKey = badgeKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}