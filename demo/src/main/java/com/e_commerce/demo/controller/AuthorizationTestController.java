package com.e_commerce.demo.controller;

import com.e_commerce.demo.security.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorization")
public class AuthorizationTestController {

    private final AuthorizationService authorizationService;


    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String customerEndpoint(){
        return "Customer endpoint";
    }

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('SELLER')")
    public String sellerEndpoint(){
        return "seller endpoint";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminEndpoint(){
        return authorizationService.adminOperation();
    }

    @GetMapping("/preduct-management")
    @PreAuthorize("hasAuthority('ADMIN','SELLER')")
    public String productEndpoint(){
        return authorizationService.sellerOradmin();
    }
}
