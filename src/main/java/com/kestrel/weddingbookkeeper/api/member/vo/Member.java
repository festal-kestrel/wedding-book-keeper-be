package com.kestrel.weddingbookkeeper.api.member.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import java.time.LocalDateTime;

public class Member extends BaseEntity {

    private Integer id;
    private String email;
    private String name;
    private String gender;
    private LocalDateTime createdAt;

    public Member(Integer id, String email, String name, String gender, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
