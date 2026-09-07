package com.e_commerce.demo.component;

import com.e_commerce.demo.Role.RoleName;
import com.e_commerce.demo.entity.Role;
import com.e_commerce.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args){
            createRoleIfNotExists(RoleName.ADMIN.name(), "System Administration");
            createRoleIfNotExists(RoleName.SELLER.name(), "Product Seller");
            createRoleIfNotExists(RoleName.CUSTOMER.name(), "Customer");

        }

    private void createRoleIfNotExists(String name,String description){
        if(!roleRepository.existsByName(name)){
            roleRepository.save(Role.builder().name(name).description(description).build());
        }
    }
}
