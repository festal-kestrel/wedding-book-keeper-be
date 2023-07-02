package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DonationReceiptResponse {

    private String groomName;
    private String brideName;
    private int donationAmount;
    private LocalDateTime weddingDate;

    public DonationReceiptResponse(MemberWedding memberWedding) {
        this.groomName = memberWedding.getWeddingId().getGroomName();
        this.brideName = memberWedding.getWeddingId().getBrideName();
        this.donationAmount = memberWedding.getDonationAmount();
        this.weddingDate = memberWedding.getWeddingId().getWeddingDate();
    }
}
