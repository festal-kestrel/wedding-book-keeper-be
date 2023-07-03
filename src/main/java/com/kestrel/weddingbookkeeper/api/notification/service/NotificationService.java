package com.kestrel.weddingbookkeeper.api.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.kestrel.weddingbookkeeper.api.wedding.dao.MemberWeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.dao.WeddingDao;
import com.kestrel.weddingbookkeeper.api.wedding.vo.NotificationInfo;
import com.kestrel.weddingbookkeeper.api.wedding.vo.Wedding;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    public void sendNotification() {
        System.out.println("==================");
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startTime = currentTime.minusMinutes(5);
        LocalDateTime endTime = currentTime;
//        LocalDateTime endTime = currentTime.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
//        System.out.println("startTime = " + fiveMinutesBefore);
//        System.out.println("endTime = " + endTime);
        List<Wedding> weddingList = weddingDao.findWeddingsWithinFiveMinutes(currentTime);

        System.out.println("weddingList = " + weddingList);
        for (Wedding wedding : weddingList) {
//            System.out.println("wedding = " + wedding);
            sendFCMNotification(wedding.getWeddingId());
        }

    }

    public void sendFCMNotification(int weddingId) {

        List<NotificationInfo> tokenList = memberWeddingDao.selectFcmTokenByWeddingId(weddingId);
        // This registration token comes from the client FCM SDKs.
//        String registrationToken = "eg0kH4JlTo6f6cnlhTJV-T:APA91bEufkoV0-0u0S66lczsStLxJJgiPPvTyvusmN3sF4MD_PcoKv4oRorNbalWlbIPzIjyy78ShQkA1y7KqUdjgOTsU3AYPY0uvSEvloMhriwAILVMvoigxgImQOf4PmJb0LxhmdJa";

        for (NotificationInfo notificationInfo : tokenList) {
            String fcmToken = notificationInfo.getFcmToken();
            System.out.println("fcmToken = " + fcmToken);

//            try{
            Message message = Message.builder()
                    .putData("title", "결혼 5분전")
                    .putData("date", "TODAY")
                    .setToken(fcmToken)
                    .build();
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            }


            String response;

            {
                try {
                    response = FirebaseMessaging.getInstance().send(message);
                    System.out.println("response = " + response);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

//        System.out.println("memberList = " + memberList);
        // See documentation on defining a message payload.

    }


}
