package com.comma.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "require_exp", nullable = false)
    private Integer requireExp;

    @Column(name = "storage_exp", nullable = false)
    private Integer storageExp;

    // Getters and Setters

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRequireExp() {
        return requireExp;
    }

    public void setRequireExp(Integer requireExp) {
        this.requireExp = requireExp;
    }

    public Integer getStorageExp() {
        return storageExp;
    }

    public void setStorageExp(Integer storageExp) {
        this.storageExp = storageExp;
    }
}