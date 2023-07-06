package com.kestrel.weddingbookkeeper.api.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.vo.NotificationInfo;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class NotificationService {

    private final WeddingDao weddingDao;
    private final MemberWeddingDao memberWeddingDao;

    public NotificationService(final WeddingDao weddingDao, final MemberWeddingDao memberWeddingDao) {
        this.weddingDao = weddingDao;
        this.memberWeddingDao = memberWeddingDao;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void sendNotification() {

        List<Wedding> weddingList = weddingDao.findWeddingsWithinFiveMinutes();
        List<Long> weddingIdsList = new ArrayList<>();

        log.info("weddingList = " + weddingList);
        if (!weddingList.isEmpty()) {
            for (Wedding wedding : weddingList) {
                boolean isMsgSent = sendFCMNotification(wedding);
                if (isMsgSent) {
                    weddingIdsList.add(wedding.getWeddingId());
                }
            }
            log.info("weddingIdsList = " + weddingIdsList);
            boolean failedUpdate = weddingDao.updateWeddingProcessed(weddingIdsList) == 0;
            if (failedUpdate) {
                throw new RuntimeException();
            }
        }

    }

    public boolean sendFCMNotification(Wedding wedding) {
        List<NotificationInfo> tokenList = memberWeddingDao.selectFcmTokenByWeddingId(wedding.getWeddingId());

        for (NotificationInfo notificationInfo : tokenList) {
            String fcmToken = notificationInfo.getFcmToken();
            log.info("==============================================");
            log.info("fcmToken = " + fcmToken);
            if (fcmToken != null) {
                Message message = Message.builder()
                        .putData("title", "결혼식 전 알림")
                        .putData("groom", notificationInfo.getGroom())
                        .putData("bride", notificationInfo.getBride())
                        .putData("isGroomSide", notificationInfo.getIsGroomSide())
                        .setToken(fcmToken)
                        .build();
                try {
                    String response = FirebaseMessaging.getInstance().send(message);
                    log.info("response = " + response);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;

    }


}
