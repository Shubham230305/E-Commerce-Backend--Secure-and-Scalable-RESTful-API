package com.e_commerce.demo.repository;

import com.e_commerce.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);     //all row
    boolean existsByEmail(String email);

    @Query("""
        SELECT u
        FROM User u
        JOIN FETCH u.role
        WHERE u.email=:email
        """)
    Optional<User> findByEmailWithRole(String email);
}
