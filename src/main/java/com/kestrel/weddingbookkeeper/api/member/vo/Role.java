package com.kestrel.weddingbookkeeper.api.member.vo;

public enum Role {

    MANAGER("manager"),
    PARTNER("partner"),
    GUEST("guest");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
