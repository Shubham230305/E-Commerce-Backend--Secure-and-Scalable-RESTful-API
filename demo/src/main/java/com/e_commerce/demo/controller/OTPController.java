package com.e_commerce.demo.controller;

import com.e_commerce.demo.dto.Auth.OTPRequestDto;
import com.e_commerce.demo.dto.Auth.VerifyOTPRequest;
import com.e_commerce.demo.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OTPController {


    @Autowired
    private OTPService otpService;


    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestBody OTPRequestDto request){
        String response = otpService.sendOTP(request.getPhoneNumber());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody VerifyOTPRequest request){
        String response = otpService.verifyOTP(request.getPhoneNumber(), request.getOtp());
        return ResponseEntity.ok(response);


    }


}
