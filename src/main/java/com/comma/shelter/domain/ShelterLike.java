package com.comma.shelter.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "shelter_like")
public class ShelterLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_like_key")
    private Long shelterLikeKey;

    @Column(name = "user_key", nullable = false)
    private Long userKey;

    @Column(name = "shelter_key", nullable = false)
    private Long shelterKey;

    @Column(name = "created_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    // Getters and Setters
    public Long getShelterLikeKey() {
        return shelterLikeKey;
    }

    public void setShelterLikeKey(Long shelterLikeKey) {
        this.shelterLikeKey = shelterLikeKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Long getShelterKey() {
        return shelterKey;
    }

    public void setShelterKey(Long shelterKey) {
        this.shelterKey = shelterKey;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}