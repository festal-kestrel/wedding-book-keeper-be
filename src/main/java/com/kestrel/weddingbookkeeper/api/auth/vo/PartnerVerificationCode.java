package com.kestrel.weddingbookkeeper.api.auth.vo;

import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash(value = "partnerVerificationCode", timeToLive = 60 * 5)
public class PartnerVerificationCode implements Serializable {

    @Serial
    private static final long serialVersionUID = 3756681426810771397L;

    @Id
    private String verificationCode;

    @Indexed
    private Long memberId;

    private Role role;

    private boolean isVerified;

    public PartnerVerificationCode(String verificationCode, Role role, Long memberId) {
        this.verificationCode = verificationCode;
        this.role = role;
        this.memberId = memberId;
    }

    public static PartnerVerificationCode of(String verificationCode, Role role, Long memberId) {
        return new PartnerVerificationCode(verificationCode, role, memberId);
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Role getRole() {
        return role;
    }

    public Long getMemberId() {
        return memberId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
    }
}
