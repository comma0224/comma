package com.comma.shelter.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comments {
    private Long commentKey;
    private Long userKey;
    private Long parentCommentKey;
    private String content;
    private Timestamp createdAt;
    private Long commentLikeCount;
    private String nickname;

    public Comments(Long commentKey, Long userKey, Long parentCommentKey, String content, Timestamp createdAt, Long commentLikeCount, String nickname) {
        this.commentKey = commentKey;
        this.userKey = userKey;
        this.parentCommentKey = parentCommentKey;
        this.content = content;
        this.createdAt = createdAt;
        this.commentLikeCount = commentLikeCount;
        this.nickname = nickname;
    }

    public Long getCommentKey() {
        return commentKey;
    }

    public void setCommentKey(Long commentKey) {
        this.commentKey = commentKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Long getParentCommentKey() {
        return parentCommentKey;
    }

    public void setParentCommentKey(Long parentCommentKey) {
        this.parentCommentKey = parentCommentKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(Long commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
