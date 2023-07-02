package com.kestrel.weddingbookkeeper.api.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResponseEntity<T> {

    private static final String SUCCESS_CODE = "success";
    private static final String FAIL_CODE = "fail";
    private static final String SUCCESS_MESSAGE = "성공";

    private String code;
    private String message;
    private T data;

    public BaseResponseEntity(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponseEntity<T> success(T data) {
        return new BaseResponseEntity<>(
                SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static BaseResponseEntity<Void> success() {
        return new BaseResponseEntity<>(
                SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponseEntity<T> fail(String message) {
        return new BaseResponseEntity<>(
                FAIL_CODE, message, null);
    }
}
