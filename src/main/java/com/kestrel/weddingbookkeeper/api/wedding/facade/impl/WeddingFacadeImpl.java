package com.kestrel.weddingbookkeeper.api.wedding.facade.impl;

import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.dto.response.WeddingIdResponse;
import com.kestrel.weddingbookkeeper.api.wedding.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.service.impl.QrService;
import com.kestrel.weddingbookkeeper.external.storage.service.FileService;
import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingFacadeImpl implements WeddingFacade {

    private static final String QR_DIRECTORY = "qrcode";

    private final MemberService memberService;
    private final WeddingService weddingService;
    private final QrService qrService;
    private final FileService fileService;

    public WeddingFacadeImpl(final MemberService memberService,
                             final WeddingService weddingService,
                             final QrService qrService,
                             final FileService fileService) {
        this.memberService = memberService;
        this.weddingService = weddingService;
        this.qrService = qrService;
        this.fileService = fileService;
    }

    @Override
    @Transactional
    public void createWeddingInfo(Integer memberId, WeddingInfoRequest weddingInfoRequest) {
        Member member = memberService.getMember(memberId);
        Integer weddingId = weddingService.saveWedding(member, weddingInfoRequest);
        InputStream qr = qrService.generateQRCode(weddingId);
        String savedQrImageUrl = fileService.upload(qr, QR_DIRECTORY);
        // 데이터 수정
        weddingService.updateQrImgUrl(weddingId, savedQrImageUrl);
    }

    @Override
    public WeddingIdResponse getWeddingId(Integer memberId) {
        Member member = memberService.getMember(memberId);
        return weddingService.getWedding(member);
    }
}
