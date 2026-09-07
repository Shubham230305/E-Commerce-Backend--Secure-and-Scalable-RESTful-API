package com.e_commerce.demo.serviceImpl;

import com.e_commerce.demo.dto.RoleRequestDto;
import com.e_commerce.demo.dto.RoleResponseDto;
import com.e_commerce.demo.entity.Role;
import com.e_commerce.demo.repository.RoleRepository;
import com.e_commerce.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    private  final ModelMapper modelMapper;

    @Override
    public RoleResponseDto createRole(RoleRequestDto dto){
        Role role = modelMapper.map(dto,Role.class);
        Role savedRole = roleRepository.save(role);
        return modelMapper.map(savedRole,RoleResponseDto.class);
    }

    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, RoleResponseDto.class)).toList();

    }

    public RoleResponseDto getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return modelMapper.map(role, RoleResponseDto.class);
    }


    public RoleResponseDto updateRole(Long id, RoleRequestDto dto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        role.setDescription(dto.getDescription());
        Role role1 = roleRepository.save(role);
        return modelMapper.map(role1, RoleResponseDto.class);
    }

//    public void deleteRole(Long id) {
//        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
//        roleRepository.delete(role);
//    }


}
