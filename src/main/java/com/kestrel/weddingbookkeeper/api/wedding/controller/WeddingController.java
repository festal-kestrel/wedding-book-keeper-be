package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.wedding.vo.MemberWedding;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInfomationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private static final Integer MEMBER_ID = 1;

    private final WeddingFacade weddingFacade;
    private final WeddingService weddingService;

    public WeddingController(final WeddingFacade weddingFacade, final WeddingService weddingService) {
        this.weddingFacade = weddingFacade;
        this.weddingService = weddingService;
    }

    @PostMapping
    public void createWeddingInfo(@RequestBody final WeddingInfoRequest weddingInfoRequest) {
        weddingFacade.createWeddingInfo(MEMBER_ID, weddingInfoRequest);
    }

    @GetMapping("/{weddingId}")
    public WeddingInfoResponse selectWeddingInfo(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectWeddingInfo(weddingId);
    }

    @PostMapping("/partner")
    public void connectPartner(@RequestBody PartnerCodeRequest partnerCodeRequest) {
        weddingService.connectPartner(partnerCodeRequest, MEMBER_ID);
    }

    @PatchMapping("/{weddingId}")
    public void updateWeddingInfomation(@PathVariable("weddingId") Integer weddingId, @RequestBody WeddingUpdateInfomationRequest weddingUpdateInfomationRequest) {
        weddingService.updateWeddingInfomation(weddingId, weddingUpdateInfomationRequest);
    }

    @GetMapping("/{weddingId}/qr")
    public WeddingQrResponse selectQrImgUrl(@PathVariable("weddingId") Integer weddingId){
        return weddingService.selectQrImgUrl(weddingId);
    }

    @GetMapping
    public List<MemberWedding> selectDonationList() {
        List<MemberWedding> memberWeddingList = weddingService.selectDonationList(MEMBER_ID);
        return memberWeddingList;
    }
}
