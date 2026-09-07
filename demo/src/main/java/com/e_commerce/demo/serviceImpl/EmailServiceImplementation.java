package com.e_commerce.demo.serviceImpl;

import com.e_commerce.demo.dto.Auth.EmailRequestDto;
import com.e_commerce.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(EmailRequestDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());

        message.setText(dto.getMessage());

        mailSender.send(message);
    }
}
