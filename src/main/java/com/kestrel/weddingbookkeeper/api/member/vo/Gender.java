package com.kestrel.weddingbookkeeper.api.member.vo;

public enum Gender {
    MALE("신랑"),
    FEMALE("신부");

    private final String message;

    Gender(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
