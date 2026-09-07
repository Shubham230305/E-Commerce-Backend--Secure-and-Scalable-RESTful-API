package com.e_commerce.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
}
