package com.example.Precept.Backend.APIs.Settings.registration.OTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user/otp/send")
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }
}
