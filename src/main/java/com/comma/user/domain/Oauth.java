package com.comma.user.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "oauth")
public class Oauth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oauth_key", nullable = false)
    private Long oauthKey;

    @Column(name = "user_key", nullable = false)
    private Long userKey;

    @Column(name = "company", nullable = false, length = 255)
    private String company;

    @Column(name = "company_key", nullable = false, length = 255)
    private String companyKey;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Getters and Setters

    public Long getOauthKey() {
        return oauthKey;
    }

    public void setOauthKey(Long oauthKey) {
        this.oauthKey = oauthKey;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}