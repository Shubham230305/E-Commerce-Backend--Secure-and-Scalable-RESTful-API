package com.e_commerce.demo.service;

import com.e_commerce.demo.dto.UserRequestDto;
import com.e_commerce.demo.dto.UserResponseDto;

import java.util.List;
public interface UserService {

    UserResponseDto create(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);

}