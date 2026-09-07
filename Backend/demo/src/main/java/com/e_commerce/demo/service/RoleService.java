package com.e_commerce.demo.service;

import com.e_commerce.demo.dto.RoleRequestDto;
import com.e_commerce.demo.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {

    RoleResponseDto createRole(RoleRequestDto dto);

    RoleResponseDto getRole(Long id);

    List<RoleResponseDto> getAllRoles();

    RoleResponseDto updateRole(Long id,RoleRequestDto dto);

    //void deleteRole(Long id)
}
