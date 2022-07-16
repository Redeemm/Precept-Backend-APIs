package com.example.Precept.Backend.APIs.Settings.registration.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


public class SendEmail {

    @Autowired
    private EmailService senderService;

    public SendEmail(EmailService senderService) {
        this.senderService = senderService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        senderService.sendSimpleEmail(
                "reddev149@gmail.com",
                "This is email body",
                "This is email subject");

    }
}
