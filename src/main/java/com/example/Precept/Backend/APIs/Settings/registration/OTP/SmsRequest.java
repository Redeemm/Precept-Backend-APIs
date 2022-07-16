package com.example.Precept.Backend.APIs.Settings.registration.OTP;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Random;

public class SmsRequest {

    public  int otp() {
        int OTP;
        Random random = new Random();
        OTP = random.nextInt(99999);
        return OTP;

    }

    @NotBlank
    private final String phoneNumber; // destination

    @NotBlank
    private String message;

    public SmsRequest( @JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {

        message = "Your verification code is:" + otp() ;
        return message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber= ..." + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
