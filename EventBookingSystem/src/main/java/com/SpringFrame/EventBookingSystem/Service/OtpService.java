package com.SpringFrame.EventBookingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    private EmailService emailService;
   

    // 🔹 SEND OTP
    public void sendEmailOtp(String email) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStorage.put(email, otp);

        emailService.sendOtpEmail(email, otp);

        System.out.println("OTP for " + email + " is: " + otp);
    }

    // 🔹 VERIFY OTP
    public boolean validateOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        return storedOtp != null && storedOtp.equals(otp);
    }
}