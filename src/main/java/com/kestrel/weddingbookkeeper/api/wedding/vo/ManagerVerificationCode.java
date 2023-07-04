package com.kestrel.weddingbookkeeper.api.wedding.vo;

import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "verificationCode", timeToLive = 60 * 5)
public class ManagerVerificationCode implements Serializable {

    @Serial
    private static final long serialVersionUID = 6527745057686091758L;

    @Id
    private String verificationCode;

    @Indexed
    private Long weddingId;

    private boolean isVerified;

    public ManagerVerificationCode(String verificationCode, Long weddingId) {
        this.verificationCode = verificationCode;
        this.weddingId = weddingId;
    }

    public static ManagerVerificationCode of(String verificationCode, Long weddingId) {
        return new ManagerVerificationCode(verificationCode, weddingId);
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }
}
