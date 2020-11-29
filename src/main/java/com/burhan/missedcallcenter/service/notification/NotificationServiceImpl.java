package com.burhan.missedcallcenter.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    MessageSource messageSource;
    SimpMessagingTemplate messagingTemplate;

    NotificationServiceImpl(SimpMessagingTemplate messagingTemplate,MessageSource messageSource){
        this.messageSource=messageSource;
        this.messagingTemplate=messagingTemplate;
    }

    @Override
    public void sendNotification(String username) {
        String message = messageSource.getMessage("missed.calls", null, LocaleContextHolder.getLocale());
        messagingTemplate.convertAndSendToUser(username, "/queue/reply", message);
    }
}