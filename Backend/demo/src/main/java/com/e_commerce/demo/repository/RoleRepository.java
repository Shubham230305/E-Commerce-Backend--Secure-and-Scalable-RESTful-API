package com.e_commerce.demo.repository;

import com.e_commerce.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);   //all row
    boolean existsByName(String name);    // only name column
}

