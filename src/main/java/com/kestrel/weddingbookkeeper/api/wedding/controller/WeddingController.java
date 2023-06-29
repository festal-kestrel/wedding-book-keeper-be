package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private static final Integer MEMBER_ID = 7;

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

    @GetMapping("/{wedding_id}")
    public WeddingInfoResponse selectWeddingInfo(@PathVariable("wedding_id") Integer weddingId) {
        return weddingService.selectWeddingInfo(weddingId);
    }

    @PostMapping("/partner")
    public void connectPartner(@RequestBody PartnerCodeRequest partnerCodeRequest) {
        System.out.println("partnerCodeRequest = " + partnerCodeRequest);
        boolean isConnect = weddingService.connectPartner(partnerCodeRequest, MEMBER_ID);
        System.out.println("isConnect = " + isConnect);
    }
}
