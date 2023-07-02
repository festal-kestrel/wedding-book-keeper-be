package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class DonationReceiptsResponse {

    List<DonationReceiptResponse> donations;

    public DonationReceiptsResponse(List<DonationReceiptResponse> donations) {
        this.donations = donations;
    }
}
