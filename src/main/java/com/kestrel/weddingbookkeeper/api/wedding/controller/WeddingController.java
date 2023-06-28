package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private final WeddingService weddingService;

    public WeddingController(final WeddingService weddingService) {
        this.weddingService = weddingService;
    }

    @PostMapping
    public void createWeddingInfo(@RequestBody final WeddingInfoRequest weddingInfoRequest) {
        weddingService.createWeddingInfo(
            new WeddingInfoRequestDto(1, weddingInfoRequest.getLocation(), weddingInfoRequest.getWeddingDate())
        );
    }
}
