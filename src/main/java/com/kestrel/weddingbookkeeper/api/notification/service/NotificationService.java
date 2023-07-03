package com.kestrel.weddingbookkeeper.api.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.vo.NotificationInfo;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class NotificationService {

    private final WeddingDao weddingDao;
    private final MemberWeddingDao memberWeddingDao;

    public NotificationService(WeddingDao weddingDao, MemberWeddingDao memberWeddingDao) {
        this.weddingDao = weddingDao;
        this.memberWeddingDao = memberWeddingDao;
    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void sendNotification() {
        System.out.println("==================");

        List<Wedding> weddingList = weddingDao.findWeddingsWithinFiveMinutes();
        List<Integer> weddingIdsList = new ArrayList<>();

        System.out.println("weddingList = " + weddingList);
        if(!weddingList.isEmpty()) {
            for (Wedding wedding : weddingList) {
//            System.out.println("wedding = " + wedding);
                sendFCMNotification(wedding);
                sendFCMNotification(wedding);
                weddingIdsList.add(wedding.getWeddingId());
//            wedding.setProcessed(true);
//            weddingDao.save(wedding);
            }
            boolean failedUpdate = weddingDao.updateWeddingProcessed(weddingIdsList) == 0;
            if (failedUpdate) {
                throw new RuntimeException();
            }
        }

    }

    public void sendFCMNotification(Wedding wedding) {

        List<NotificationInfo> tokenList = memberWeddingDao.selectFcmTokenByWeddingId(wedding.getWeddingId());

        for (NotificationInfo notificationInfo : tokenList) {
            String fcmToken = notificationInfo.getFcmToken();
            System.out.println("fcmToken = " + fcmToken);

            Message message = Message.builder()
                    .putData("title", "결혼 5분전")
                    .putData("date", "TODAY")
                    .setToken(fcmToken)
                    .build();

            String response;

            try {
                response = FirebaseMessaging.getInstance().send(message);
                System.out.println("response = " + response);
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }

        }

    }


}
