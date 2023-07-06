package com.kestrel.weddingbookkeeper.api.wedding.controller;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.summary;

import com.kestrel.weddingbookkeeper.api.auth.dto.response.VerificationCodeResponse;
import com.kestrel.weddingbookkeeper.api.auth.utils.WeddingMember;
import com.kestrel.weddingbookkeeper.api.member.vo.Role;
import com.kestrel.weddingbookkeeper.api.wedding.dto.MemberWeddingDto;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.CreateWeddingResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.DonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.GuestDonationReceiptsResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.PartnerCodeRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingUpdateInformationRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingInfoResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingManagerCodeResponse;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingQrResponse;
import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Wedding API", description = "결혼식 정보 관련 API")
@RestController
@RequestMapping("/api/v1/weddings")
public class WeddingController {

    private final WeddingFacade weddingFacade;
    private final WeddingService weddingService;

    public WeddingController(final WeddingFacade weddingFacade, final WeddingService weddingService) {
        this.weddingFacade = weddingFacade;
        this.weddingService = weddingService;
    }

    @PostMapping
    @Operation(tags = {"Wedding API"}, summary = "결혼식 정보 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public CreateWeddingResponse createWeddingInfo(@RequestBody final WeddingInfoRequest weddingInfoRequest,
                                                   @AuthenticationPrincipal final WeddingMember weddingMember) {
        return weddingFacade.createWeddingInfo(weddingMember.getMemberId(), weddingInfoRequest);
    }

    @GetMapping("/{weddingId}")
    @Operation(tags = {"Wedding API"}, summary = "결혼식 상세 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingInfoResponse.class),
    })
    public WeddingInfoResponse selectWeddingInfo(@PathVariable("weddingId") Long weddingId) {
        return weddingService.selectWeddingInfo(weddingId);
    }

    @PostMapping("/partner")
    @Operation(tags = {"Wedding API"}, summary = "배우자 연결 (배제)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void connectPartner(@RequestBody PartnerCodeRequest partnerCodeRequest,
                               @AuthenticationPrincipal final WeddingMember weddingMember) {
        weddingService.connectPartner(partnerCodeRequest, weddingMember.getMemberId());
    }

    @PatchMapping("/{weddingId}")
    @Operation(tags = {"Wedding API"}, summary = "결혼식 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void updateWeddingInformation(@PathVariable("weddingId") Long weddingId,
                                         @RequestBody WeddingUpdateInformationRequest weddingUpdateInformationRequest) {
        weddingService.updateWeddingInformation(weddingId, weddingUpdateInformationRequest);
    }

    @GetMapping("/{weddingId}/admin/code")
    @Operation(tags = {"Wedding API"}, summary = "관리자 인증코드 조회 (배제)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingManagerCodeResponse.class),
    })
    public WeddingManagerCodeResponse selectManagerCode(@PathVariable("weddingId") Long weddingId) {
        return weddingService.selectManagerCode(weddingId);
    }

    @GetMapping("/{weddingId}/verification-code")
    @Operation(tags = {"Wedding API"}, summary = "웨딩 관리자 인증코드 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = VerificationCodeResponse.class),
    })
    public VerificationCodeResponse getManagerVerificationCode(@PathVariable("weddingId") Long weddingId) {
        return weddingService.getManagerVerificationCode(weddingId);
    }

    @GetMapping("/{weddingId}/qr")
    @Operation(tags = {"Wedding API"}, summary = "결혼식 QR 이미지 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingQrResponse.class),
    })
    public WeddingQrResponse selectQrImgUrl(@PathVariable("weddingId") Long weddingId) {
        return weddingService.selectQrImgUrl(weddingId);
    }

    @GetMapping
    @Operation(tags = {"Wedding API"}, summary = "나의 축의금 납부내역 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = DonationReceiptsResponse.class),
    })
    public DonationReceiptsResponse selectDonationList(@AuthenticationPrincipal final WeddingMember weddingMember) {
        return weddingService.selectDonationList(weddingMember.getMemberId());
    }

    @GetMapping("/{weddingId}/guests")
    @Operation(tags = {"Wedding API"}, summary = "하객 축의금 납부 내역 조회 (신랑신부/관리자용)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = GuestDonationReceiptsResponse.class),
    })
    public GuestDonationReceiptsResponse selectGuestList(@PathVariable("weddingId") Long weddingId,
                                                         @RequestParam Role role) {
        return weddingService.getWeddingGuestsInformation(weddingId, role);
    }

    @PostMapping("/{weddingId}/guests/new")
    @Operation(tags = {"Wedding API"}, summary = "하객 QR 입장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void createMemberWedding(@PathVariable("weddingId") Long weddingId,
                                    @RequestBody MemberWeddingDto memberWeddingDto,
                                    @AuthenticationPrincipal final WeddingMember weddingMember) {
        weddingService.createMemberWeddingInfo(weddingId, weddingMember.getMemberId(), memberWeddingDto);
    }

    @GetMapping("/me")
    @Operation(tags = {"Wedding API"}, summary = "나의 WeddingId 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = WeddingIdResponse.class),
    })
    public WeddingIdResponse selectMyWeddingId(@AuthenticationPrincipal final WeddingMember weddingMember) {
        return weddingFacade.getWeddingId(weddingMember.getMemberId());
    }

    @PatchMapping("/{weddingId}/guests/{guestId}/paid/approval")
    @Operation(tags = {"Wedding API"}, summary = "축의금 납부 승인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void patchDonationApproval(@PathVariable("weddingId") Long weddingId,
                                      @PathVariable("guestId") Long guestId) {
        weddingService.patchDonationApproval(weddingId, guestId);
    }

    @PatchMapping("/{weddingId}/guests/{guestId}/paid/rejection")
    @Operation(tags = {"Wedding API"}, summary = "축의금 납부 반려")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation"),
    })
    public void patchDonationRejection(@PathVariable("weddingId") Long weddingId,
                                       @PathVariable("guestId") Long guestId) {
        weddingService.patchDonationRejection(weddingId, guestId);
    }
}
