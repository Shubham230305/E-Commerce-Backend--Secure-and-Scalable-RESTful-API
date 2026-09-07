package com.e_commerce.demo.serviceImpl;

import com.e_commerce.demo.service.OTPService;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImplementation implements OTPService {

    @Value("${twilio.verify.service.sid}")

    private String serviceSID;

    @Override
    public String sendOTP(String phoneNumber){
        Verification verification = Verification.creator(serviceSID, phoneNumber, "sms").create();
        return verification.getStatus();
    }

    @Override
    public String verifyOTP(String phoneNumber, String otp){
        VerificationCheck verificationCheck = VerificationCheck.creator(serviceSID)
                        .setTo(phoneNumber)
                        .setCode(otp)
                        .create();

        return verificationCheck.getStatus();
    }

}
