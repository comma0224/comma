package com.comma.shelter.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post_like")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_key", nullable = false)
    private Long postLikeKey;

    @Column(name = "user_key", nullable = false)
    private Long userKey;

    @Column(name = "post_key", nullable = false)
    private Long postKey;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    // Getters and Setters

    public Long getPostLikeKey() {
        return postLikeKey;
    }

    public void setPostLikeKey(Long postLikeKey) {
        this.postLikeKey = postLikeKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Long getPostKey() {
        return postKey;
    }

    public void setPostKey(Long postKey) {
        this.postKey = postKey;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}