package com.e_commerce.demo.service;

public interface OTPService {
    public String sendOTP(String phoneNumber);
    public String verifyOTP(String phoneNumber, String otp);
}
