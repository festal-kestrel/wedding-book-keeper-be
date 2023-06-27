package com.kestrel.weddingbookkeeper.api.wedding.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;

public class MemberWedding extends BaseEntity {

    private Member member;
    private Wedding wedding;
    private int donationAmount;
    private boolean hasPaid;
    private String relation;

    public MemberWedding(Member member, Wedding wedding, int donationAmount, boolean hasPaid, String relation) {
        this.member = member;
        this.wedding = wedding;
        this.donationAmount = donationAmount;
        this.hasPaid = hasPaid;
        this.relation = relation;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
