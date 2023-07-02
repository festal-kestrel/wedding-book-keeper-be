package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.GuestDonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void updateWeddingInformation(@PathVariable("weddingId") Integer weddingId,
                                         @RequestBody WeddingUpdateInformationRequest weddingUpdateinformationRequest) {
        weddingService.updateWeddinginformation(weddingId, weddingUpdateinformationRequest);
    }

    @GetMapping("/{weddingId}/admin/code")
    public WeddingManagerCodeResponse selectManagerCode(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectManagerCode(weddingId);
    }

    @GetMapping("/{weddingId}/qr")
    public WeddingQrResponse selectQrImgUrl(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectQrImgUrl(weddingId);
    }

    @GetMapping
    public DonationReceiptsResponse selectDonationList() {
        return weddingService.selectDonationList(MEMBER_ID);
    }

    @GetMapping("/{weddingId}/guests")
    public GuestDonationReceiptsResponse selectGuestList(@PathVariable("weddingId") Integer weddingId,
                                                         @RequestParam Role role) {
        return weddingService.getWeddingGuestsInformation(weddingId, role);
    }
}
