package com.kestrel.weddingbookkeeper.api.wedding.vo;

import lombok.Data;

@Data
public class NotificationInfo {

    private String groom;
    private String bride;
    private String fcmToken;
    private String isGroomSide;
}
