package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class GuestDonationReceiptResponse {

    private String guestName;
    private String guestSide;
    private String relation;
    private boolean hasPaid;
    private int donationAmount;
    private LocalDateTime weddingDate;

    public GuestDonationReceiptResponse(MemberWedding memberWedding) {
        this.guestName = memberWedding.getGuestName();
        this.guestSide = memberWedding.isGroomSide() ? "신랑측" : "신부측";
        this.relation = memberWedding.getRelation();
        this.hasPaid = memberWedding.isHasPaid();
        this.donationAmount = memberWedding.getDonationAmount();
        this.weddingDate = memberWedding.getWeddingId().getWeddingDate();
    }
}
