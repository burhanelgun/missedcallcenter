package com.burhan.missedcallcenter.service.notification;

import com.burhan.missedcallcenter.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private SimpMessagingTemplate messagingTemplate;

    NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendNotification(String phone, String message) {
        if (message != null && message.length() != 0) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setMessage(message);
            messagingTemplate.convertAndSendToUser(phone, "/queue/reply", notificationDto);
            log.info("A notification was sent to user of: " + phone + ", with message:" + message);
        }
    }

}
