package com.comma.user.domain;

public class Users {
    private Long userKey;
    private Integer level;
    private Integer exp;
    private Integer requireExp;
    private Integer cash;
    private Integer point;
    private String nickname;

    public Users(Long userKey, Integer level, Integer exp, Integer requireExp, Integer cash, Integer point, String nickname) {
        this.userKey = userKey;
        this.level = level;
        this.exp = exp;
        this.requireExp = requireExp;
        this.cash = cash;
        this.point = point;
        this.nickname = nickname;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getRequireExp() {
        return requireExp;
    }

    public void setRequireExp(Integer requireExp) {
        this.requireExp = requireExp;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
