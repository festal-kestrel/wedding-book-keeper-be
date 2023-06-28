package com.kestrel.weddingbookkeeper.api.member.facade.impl;

import com.kestrel.weddingbookkeeper.api.member.facade.WeddingFacade;
import com.kestrel.weddingbookkeeper.api.member.service.MemberService;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.api.wedding.service.impl.QrService;
import com.kestrel.weddingbookkeeper.external.storage.FileService;
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
    public void createWeddingInfo(WeddingInfoRequestDto weddingInfoRequestDto) {
        Member member = memberService.getMember(weddingInfoRequestDto.getMemberId());
        InputStream qr = qrService.generateQRCode(weddingInfoRequestDto);
        String savedQrImageUrl = fileService.upload(qr, QR_DIRECTORY);
        weddingService.saveWedding(member, weddingInfoRequestDto, savedQrImageUrl);
    }
}
