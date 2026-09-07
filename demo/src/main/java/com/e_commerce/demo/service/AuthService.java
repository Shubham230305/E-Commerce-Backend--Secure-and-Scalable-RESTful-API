package com.e_commerce.demo.service;

import com.e_commerce.demo.dto.Auth.AuthResponseDto;
import com.e_commerce.demo.dto.Auth.LoginRequestDto;
import com.e_commerce.demo.dto.Auth.RegisterRequestDto;


public interface AuthService {

    AuthResponseDto register(RegisterRequestDto requestDto);
    AuthResponseDto login(LoginRequestDto requestDto);
}
