package com.comma.shelter.domain;

import java.sql.Timestamp;

public class Posts {
    private Long postKey;
    private String title;
    private Integer views;
    private Timestamp createdAt;
    private String nickname;
    private String tagTitle;
    private Long postLikeCount;
    private Long commentCount;
    private String content;
    private Long userKey;


    public Posts(Long postKey, String title, Integer views, Timestamp createdAt, String nickname, String tagTitle, Long postLikeCount, Long commentCount) {
        this.postKey = postKey;
        this.title = title;
        this.views = views;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.tagTitle = tagTitle;
        this.postLikeCount = postLikeCount;
        this.commentCount = commentCount;
    }

    public Posts(Long postKey, String title, Integer views, Timestamp createdAt, String nickname, String tagTitle, Long postLikeCount, Long commentCount, String content, Long userKey) {
        this.postKey = postKey;
        this.title = title;
        this.views = views;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.tagTitle = tagTitle;
        this.postLikeCount = postLikeCount;
        this.commentCount = commentCount;
        this.content = content;
        this.userKey = userKey;
    }

    public Long getPostKey() {
        return postKey;
    }

    public void setPostKey(Long postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public Long getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(Long postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }
}

