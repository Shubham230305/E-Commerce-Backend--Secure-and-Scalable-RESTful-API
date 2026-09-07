package com.e_commerce.demo.dto.Auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    private Long id;
    private String name;
    private String email;
    private String Message;
    private String role;
    private String mobile;
    private String token;
}
