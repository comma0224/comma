package com.comma.user.domain;

public class UserBadges {
    private Long userBadgeKey;
    private Long badgeKey;
    private Long userKey;
    private String name;
    private String file;
    private Integer tier;

    public UserBadges(Long userBadgeKey, Long badgeKey, Long userKey, String name, String file, Integer tier) {
        this.userBadgeKey = userBadgeKey;
        this.badgeKey = badgeKey;
        this.userKey = userKey;
        this.name = name;
        this.file = file;
        this.tier = tier;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}
