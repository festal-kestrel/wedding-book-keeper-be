package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DetailResponse;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private final WeddingService weddingService;

    public WeddingController(WeddingService weddingService) {
        this.weddingService = weddingService;
    }

    @GetMapping("/{weddingId}")
    public ResponseEntity<DetailResponse> getWeddingInfo(@PathVariable Integer weddingId) {
        Wedding wedding = weddingService.getWeddingInfo(weddingId);

        if (wedding == null) {
            return ResponseEntity.notFound().build();
        }

        String groomName = (wedding.getGroomName() != null) ? wedding.getGroomName() : "미등록";
        String brideName = (wedding.getBrideName() != null) ? wedding.getBrideName() : "미등록";

        return ResponseEntity.ok(new DetailResponse(wedding.getWeddingDate(), groomName, brideName, wedding.getLocation()));
    }
}
