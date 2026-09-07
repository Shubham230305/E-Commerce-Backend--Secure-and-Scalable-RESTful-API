package com.e_commerce.demo.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor    //auto make the construtor when it is required
public class RegisterRequestDto {

    @NotBlank(message = "Name is Required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Name is Required")
    @Size(min=8,max = 20)
    private String password;

    private String mobile;

}
