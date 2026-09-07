package com.e_commerce.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleRequestDto {

    @NotBlank(message = "Role name is Necessary")
    private String name;

    @Size(min = 8,max = 255)
    private String description;
}
