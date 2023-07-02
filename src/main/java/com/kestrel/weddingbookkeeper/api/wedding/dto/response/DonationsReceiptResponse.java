package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class DonationsReceiptResponse {

    List<DonationReceiptResponse> donations;

    public DonationsReceiptResponse(List<DonationReceiptResponse> donations) {
        this.donations = donations;
    }
}
