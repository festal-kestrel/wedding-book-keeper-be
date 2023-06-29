package com.kestrel.weddingbookkeeper.api.wedding.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kestrel.weddingbookkeeper.api.wedding.dto.request.WeddingInfoRequest;
import com.kestrel.weddingbookkeeper.api.wedding.exception.QRCodeGenerationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class QrService {

    private static final String SERVICE_NAME = "weddingBookKeeper";

    public InputStream generateQRCode(Integer weddingId) {
        // QR 코드에 포함할 데이터 생성
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("serviceName", SERVICE_NAME);
        dataMap.put("weddingId", weddingId);
        String content = buildQRContent(dataMap);

        // QR 코드 생성
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (WriterException | IOException e) {
            throw new QRCodeGenerationException();
        }
    }

    private String buildQRContent(Map<String, Object> dataMap) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
