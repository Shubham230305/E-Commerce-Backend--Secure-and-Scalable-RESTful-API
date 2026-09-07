package com.e_commerce.demo.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {


    @PreAuthorize("hasAuthority('SELLER')")
    public String adminOperation(){
        return "seller endpoint";
    }

    @PreAuthorize("hasAuthority('ADMIN','SELLER')")
    public String sellerOradmin(){
        return "admin/seller endpoint";
    }
}
