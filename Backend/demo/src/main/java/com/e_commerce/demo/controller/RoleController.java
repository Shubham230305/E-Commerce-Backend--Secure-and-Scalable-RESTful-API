package com.e_commerce.demo.controller;

import com.e_commerce.demo.dto.RoleRequestDto;
import com.e_commerce.demo.dto.RoleResponseDto;
import com.e_commerce.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(dto));
    }
    
    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }


//    @PutMapping("/{id}")
//    public RoleResponseDto updateRole(
//            @PathVariable Long id,
//            @RequestBody RoleRequestDto dto){
//
//        return roleService.updateRole(id,dto);
//    }

//    @DeleteMapping("/{id}")
//    public String deleteRole(@PathVariable Long id){
//        roleService.deleteRole(id);
//        return "Role Deleted Successfully";
//    }

}