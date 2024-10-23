package com.comma.shelter.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post", indexes = {
    @Index(name = "category_key", columnList = "category_key"),
    @Index(name = "tag_key", columnList = "tag_key"),
    @Index(name = "user_key", columnList = "user_key")
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_key", nullable = false)
    private Long postKey;

    @Column(name = "category_key", nullable = false)
    private Long categoryKey;

    @Column(name = "tag_key", nullable = false)
    private Long tagKey;

    @Column(name = "user_key", nullable = false)
    private Long userKey;

    @Column(name = "title", nullable = false, length = 255, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_bin")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT COLLATE utf8mb4_bin")
    private String content;

    @Column(name = "views", nullable = true, columnDefinition = "INT DEFAULT 0")
    private Integer views = 0;

    @Column(name = "status", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '0: 사용\\r\\n1: 미사용'")
    private Integer status = 0;

    @Column(name = "created_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

    // Getters and Setters

    public Long getPostKey() {
        return postKey;
    }

    public void setPostKey(Long postKey) {
        this.postKey = postKey;
    }

    public Long getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Long categoryKey) {
        this.categoryKey = categoryKey;
    }

    public Long getTagKey() {
        return tagKey;
    }

    public void setTagKey(Long tagKey) {
        this.tagKey = tagKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}