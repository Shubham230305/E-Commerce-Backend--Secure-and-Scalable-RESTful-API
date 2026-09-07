package com.e_commerce.demo.controller;

import com.e_commerce.demo.dto.Auth.AuthResponseDto;
import com.e_commerce.demo.dto.Auth.EmailRequestDto;
import com.e_commerce.demo.dto.Auth.LoginRequestDto;
import com.e_commerce.demo.dto.Auth.RegisterRequestDto;
import com.e_commerce.demo.service.AuthService;
import com.e_commerce.demo.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService mailService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(requestDto));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto){
        return ResponseEntity
                .ok(authService.login(requestDto));
    }
    
//    @PostMapping("/send")
//    public void sendMail(@RequestBody EmailRequestDto dto) {
//        mailService.sendMail(dto);
//    }

}
