package com.e_commerce.demo.service;

import com.e_commerce.demo.dto.Auth.EmailRequestDto;

public interface EmailService {
    void sendMail(EmailRequestDto dto);
}
