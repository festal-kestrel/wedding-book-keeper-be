package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import java.util.List;
import lombok.Getter;

@Getter
public class GuestDonationReceiptsResponse {

    private String groomName;
    private String brideName;
    private List<GuestDonationReceiptResponse> guests;

    public GuestDonationReceiptsResponse(List<MemberWedding> memberWeddings) {
        this.groomName = memberWeddings.get(0).getWeddingId().getGroomName();
        this.brideName = memberWeddings.get(0).getWeddingId().getBrideName();
        this.guests = memberWeddings.stream()
                .map(GuestDonationReceiptResponse::new).toList();
    }
}
