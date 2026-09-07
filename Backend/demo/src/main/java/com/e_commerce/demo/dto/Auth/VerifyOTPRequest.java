package com.e_commerce.demo.dto.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOTPRequest {

    private String phoneNumber;

    private String otp;


}
