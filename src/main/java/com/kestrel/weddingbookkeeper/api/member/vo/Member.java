package com.kestrel.weddingbookkeeper.api.member.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;

public class Member extends BaseEntity {

    private Integer id;
    private String email;
    private String name;
    private Gender gender;

    public Member() {
    }

    public Member(Integer id, String email, String name, Gender gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
