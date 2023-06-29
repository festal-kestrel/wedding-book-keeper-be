package com.kestrel.weddingbookkeeper.api.wedding.controller;

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
    public ResponseEntity<WeddingResponse> getWeddingInfo(@PathVariable Integer weddingId) {
        Wedding wedding = weddingService.getWeddingInfo(weddingId);

        if (wedding == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new WeddingResponse(wedding.getWeddingDate(),
                wedding.getGroom().getName(),
                wedding.getBride().getName()));
    }

    private static class WeddingResponse {
        private final LocalDateTime weddingDate;
        private final String groomName;
        private final String brideName;

        public WeddingResponse(LocalDateTime weddingDate, String groomName, String brideName) {
            this.weddingDate = weddingDate;
            this.groomName = groomName;
            this.brideName = brideName;
        }

        public LocalDateTime getWeddingDate() {
            return weddingDate;
        }

        public String getGroomName() {
            return groomName;
        }

        public String getBrideName() {
            return brideName;
        }
    }
}
