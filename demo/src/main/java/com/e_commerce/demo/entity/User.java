package com.e_commerce.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    private String name;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(length = 15)
    private String mobile;

    @Column(nullable = false,length = 255)
    private String password;

    private String address;
    private LocalDate dateOfBirth;
    @Builder.Default
    @Column(
            name = "is_account_isActive",
            nullable = false
    )
    private Boolean isActive = true;

    @Builder.Default
    @Column(
            name = "is_email_verified",
            nullable = false
    )
    private Boolean emailVerfied = false;

    @Builder.Default
    @Column(
            name = "is_account_nonlock",
            nullable = false
    )
    private Boolean accountNonLocked = true;

    @Builder.Default
    @Column(
            name = "is_account_accountNonExpired",
            nullable = false
    )
    private Boolean accountNonExpired = true;

    @Builder.Default
    @Column(
            name = "Credientials_Nonexpired",
            nullable = false
    )
    private Boolean credentialsNonExpired = true;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id",nullable = false,foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;
}

