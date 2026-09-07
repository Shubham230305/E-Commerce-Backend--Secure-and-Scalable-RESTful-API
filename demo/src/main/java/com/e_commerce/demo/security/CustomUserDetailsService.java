package com.e_commerce.demo.security;

import com.e_commerce.demo.constants.ErrorMessages;
import com.e_commerce.demo.entity.User;
import com.e_commerce.demo.exception.ResourceNotFoundException;
import com.e_commerce.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user  = userRepository.findByEmailWithRole(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(ErrorMessages.INVLAID_EMAIL_OR_PASSWORD));
        return new UserPrincipal(user);

    }



}
