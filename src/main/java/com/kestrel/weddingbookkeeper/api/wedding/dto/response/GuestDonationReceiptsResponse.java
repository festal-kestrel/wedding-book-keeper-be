package com.kestrel.weddingbookkeeper.api.wedding.dto.response;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.util.List;
import lombok.Getter;

@Getter
public class GuestDonationReceiptsResponse {

    private String groomName;
    private String brideName;
    private List<GuestDonationReceiptResponse> guests;

    public GuestDonationReceiptsResponse(Wedding wedding, List<MemberWedding> memberWeddings) {
        this.groomName = wedding.getGroomName();
        this.brideName = wedding.getBrideName();
        this.guests = memberWeddings.stream()
                .map(GuestDonationReceiptResponse::new).toList();
    }
}
