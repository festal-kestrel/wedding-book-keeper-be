package com.kestrel.weddingbookkeeper.api.wedding.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import java.time.LocalDateTime;

public class Wedding extends BaseEntity {

    private Integer id;
    private Member groom;
    private Member bride;
    private String qrImgUrl;
    private String partnerCode;
    private String managerCode;
    private String location;
    private LocalDateTime weddingDate;

    public Wedding() {
    }

    public Wedding(Integer id,
                   Member groom,
                   Member bride,
                   String qrImgUrl,
                   String partnerCode,
                   String managerCode,
                   String location,
                   LocalDateTime weddingDate) {
        this.id = id;
        this.groom = groom;
        this.bride = bride;
        this.qrImgUrl = qrImgUrl;
        this.partnerCode = partnerCode;
        this.managerCode = managerCode;
        this.location = location;
        this.weddingDate = weddingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getGroom() {
        return groom;
    }

    public void setGroom(Member groom) {
        this.groom = groom;
    }

    public Member getBride() {
        return bride;
    }

    public void setBride(Member bride) {
        this.bride = bride;
    }

    public String getQrImgUrl() {
        return qrImgUrl;
    }

    public void setQrImgUrl(String qrImgUrl) {
        this.qrImgUrl = qrImgUrl;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(LocalDateTime weddingDate) {
        this.weddingDate = weddingDate;
    }
}
