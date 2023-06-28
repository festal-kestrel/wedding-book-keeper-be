package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.kestrel.weddingbookkeeper.api.member.dao.MemberDao;
import com.kestrel.weddingbookkeeper.api.member.exception.MemberNotFountException;
import com.kestrel.weddingbookkeeper.api.member.vo.Member;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dto.WeddingInfoRequestDto;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingService;
import com.kestrel.weddingbookkeeper.external.storage.FileUploader;
import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingServiceImpl implements WeddingService {

    private final MemberDao memberDao;
    private final WeddingDao weddingDao;
    private final QrService qrService;
    private final FileUploader fileUploader;
    private static final String QR_DIRECTORY = "qrcode";

    public WeddingServiceImpl(final MemberDao memberDao, final WeddingDao weddingDao, final QrService qrService, final FileUploader fileUploader) {
        this.memberDao = memberDao;
        this.weddingDao = weddingDao;
        this.qrService = qrService;
        this.fileUploader = fileUploader;
    }

    @Override
    @Transactional
    public void createWeddingInfo(final WeddingInfoRequestDto weddingInfoRequestDto) {
        Member member = memberDao.selectById(weddingInfoRequestDto.getMemberId());
        if (member == null) {
            throw new MemberNotFountException();
        }
        InputStream qr = qrService.generateQRCode(weddingInfoRequestDto);
        String savedQrImageUrl = fileUploader.upload(qr, QR_DIRECTORY);
    }
}