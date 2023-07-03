package com.kestrel.weddingbookkeeper.api.wedding.controller;

import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingDto;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("결혼식 정보 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void createWeddingInfo(@RequestBody final WeddingInfoRequest weddingInfoRequest) {
        weddingFacade.createWeddingInfo(MEMBER_ID, weddingInfoRequest);
    }

    @GetMapping("/{weddingId}")
    @ApiOperation("결혼식 상세 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingInfoResponse.class),
    })
    public WeddingInfoResponse selectWeddingInfo(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectWeddingInfo(weddingId);
    }

    @PostMapping("/partner")
    @ApiOperation("배우자 연결 (배제)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void connectPartner(@RequestBody PartnerCodeRequest partnerCodeRequest) {
        weddingService.connectPartner(partnerCodeRequest, MEMBER_ID);
    }

    @PatchMapping("/{weddingId}")
    @ApiOperation("결혼식 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void updateWeddingInformation(@PathVariable("weddingId") Integer weddingId,
                                         @RequestBody WeddingUpdateInformationRequest weddingUpdateinformationRequest) {
        weddingService.updateWeddinginformation(weddingId, weddingUpdateinformationRequest);
    }

    @GetMapping("/{weddingId}/admin/code")
    @ApiOperation("관리자 인증코드 조회 (배제)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingManagerCodeResponse.class),
    })
    public WeddingManagerCodeResponse selectManagerCode(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectManagerCode(weddingId);
    }

    @GetMapping("/{weddingId}/qr")
    @ApiOperation("결혼식 QR 이미지 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingQrResponse.class),
    })
    public WeddingQrResponse selectQrImgUrl(@PathVariable("weddingId") Integer weddingId) {
        return weddingService.selectQrImgUrl(weddingId);
    }

    @GetMapping
    @ApiOperation("나의 축의금 납부내역 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = DonationReceiptsResponse.class),
    })
    public DonationReceiptsResponse selectDonationList() {
        return weddingService.selectDonationList(MEMBER_ID);
    }

    @GetMapping("/{weddingId}/guests")
    @ApiOperation("하객 축의금 납부 내역 조회 (신랑신부/관리자용)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = GuestDonationReceiptsResponse.class),
    })
    public GuestDonationReceiptsResponse selectGuestList(@PathVariable("weddingId") Integer weddingId,
                                                         @RequestParam Role role) {
        return weddingService.getWeddingGuestsInformation(weddingId, role);
    }

    @PostMapping("/{weddingId}/guests/new")
    @ApiOperation("하객 QR입장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void createMemberWedding(@PathVariable("weddingId") Integer weddingId,
                                    @RequestBody MemberWeddingDto memberWeddingDto) {
        weddingService.createMemberWeddingInfo(weddingId,MEMBER_ID,memberWeddingDto);
    }
}
