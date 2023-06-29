package com.kestrel.weddingbookkeeper.api.memberWedding.controller;

import com.kestrel.weddingbookkeeper.api.memberWedding.service.MemberWeddingService;
import com.kestrel.weddingbookkeeper.api.memberWedding.vo.MemberWedding;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weddings")
public class MemberWeddingController {

    private static final Integer memberId = 1;
    private final MemberWeddingService memberWeddingService;

    public MemberWeddingController(final MemberWeddingService memberWeddingService){
        this.memberWeddingService = memberWeddingService;
    }

    @GetMapping
    public List<MemberWedding> selectDonationList() {
        List<MemberWedding> memberWeddingList = memberWeddingService.selectDonationList(memberId);
        return memberWeddingList;
    }

}
