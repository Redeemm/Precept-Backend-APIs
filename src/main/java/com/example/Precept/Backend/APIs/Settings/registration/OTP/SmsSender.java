package com.example.Precept.Backend.APIs.Settings.registration.OTP;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}
