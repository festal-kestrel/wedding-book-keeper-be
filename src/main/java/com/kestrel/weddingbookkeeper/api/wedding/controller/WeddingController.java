package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private static final Integer MEMBER_ID = 1;

    private final WeddingFacade weddingFacade;

    public WeddingController(final WeddingFacade weddingFacade) {
        this.weddingFacade = weddingFacade;
    }

    @PostMapping
    public void createWeddingInfo(@RequestBody final WeddingInfoRequest weddingInfoRequest) {
        weddingFacade.createWeddingInfo(MEMBER_ID, weddingInfoRequest);
    }
}
