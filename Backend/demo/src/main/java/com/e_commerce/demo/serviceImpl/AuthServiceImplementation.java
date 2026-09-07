package com.e_commerce.demo.serviceImpl;

import com.e_commerce.demo.Role.RoleName;
import com.e_commerce.demo.constants.ErrorMessages;
import com.e_commerce.demo.constants.SuccessMessages;
import com.e_commerce.demo.dto.Auth.LoginRequestDto;
import com.e_commerce.demo.entity.Role;
import com.e_commerce.demo.entity.User;
import com.e_commerce.demo.dto.Auth.AuthResponseDto;
import com.e_commerce.demo.dto.Auth.RegisterRequestDto;
import com.e_commerce.demo.exception.ResourceAlreadyExistsException;
import com.e_commerce.demo.exception.ResourceNotFoundException;
import com.e_commerce.demo.repository.RoleRepository;
import com.e_commerce.demo.repository.UserRepository;
import com.e_commerce.demo.security.UserPrincipal;
import com.e_commerce.demo.security.jwt.JwtService;
import com.e_commerce.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

//    private final EmailService emailService;

    @Transactional()
    @Override
    public AuthResponseDto register(RegisterRequestDto requestDto) {
        //1.Normalize
        //working differ
        String email = normalizeEmail(requestDto.getEmail());

        String name = normalizeName(requestDto.getName());
        String mobile = normalizePhone(requestDto.getMobile());

        //2.Check duplicate
        if (userRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXIST);
        }


        // 3.Get Default Customer role
        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER.name())
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));

        //4.Encode Password
        String rawPassword = normalizePassword(requestDto.getPassword());
        String encodePassword = passwordEncoder.encode(rawPassword);

        //5.Build User Entity
        User user = User.builder()
                .name(name)
                .email(email)
                .mobile(mobile)
                .password(encodePassword)
                .role(customerRole)//backend will manage this

                .build();

        //6.Save User
        User savedUser = userRepository.save(user);

        // emailService.sendWelcomeEmail(
        //         savedUser.getEmail(),
        //         savedUser.getName());

        // 8. Return
          return BuildAuthResponse(savedUser,SuccessMessages.REGISTER_SUCCESS);
    }


    @Override
    @Transactional(readOnly = true)
    public AuthResponseDto login(LoginRequestDto requestDto) {
        //1.Normalize
        String email = normalizeEmail(requestDto.getEmail());
        String rawPassword = normalizePassword(requestDto.getPassword());

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(email,rawPassword)
      );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        User user = principal.getUser();


        String token = jwtService.generateToken(principal);

        //3.Check Account Status
//        if(!user.getIsActive()){
//            throw new UnauthorizedException(ErrorMessages.ACCOUNT_DISABLED);
//        }
//
//        //4.Check account Lock
//        if(!user.getAccountNonLocked()){
//            throw new UnauthorizedException(ErrorMessages.ACCOUNT_LOCKED);
//        }

        //5.Email verification (after implementation the email verify)
//        if(!user.getEmailVerfied()){
//            throw new UnauthorizedException(ErrorMessages.EMAIL_NOT_VERIFIED);
//        }

        //6.Verify Password
//        boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());
//
//        if(!passwordMatches){
//            throw new UnauthorizedException(
//                    ErrorMessages.INVLAID_EMAIL_OR_PASSWORD);
//        }


        //7.Return the response
      AuthResponseDto response = BuildAuthResponse(user,SuccessMessages.LOGIN_SUCCESSFUL);
      response.setToken(token);
      return response;
    }


    //helper Method for the Model mapper
    private AuthResponseDto BuildAuthResponse(User user,String message){

        AuthResponseDto response = modelMapper.map(user, AuthResponseDto.class);

        response.setRole(user.getRole().getName());
        response.setMessage(message);
        return response;
    }


    //These are the Helper Methods

    private String normalizeEmail(String email){
        return email.trim().toLowerCase();
    }

    private String normalizeName(String name){
        return name.trim();
    }

    private String normalizePassword(String password){
        return password.trim();
    }

    //as per the IT standards it is good practice to use the seperate methods proper in use of the SRP Principle
    private String normalizePhone(String phone){
        return phone.trim();
    }
//
//    private User findUserByEmail(String email){
//        return userRepository.findByEmail(email).orElseThrow(() -> {
//           return new UnauthorizedException(ErrorMessages.INVLAID_EMAIL_OR_PASSWORD);
//        });

}

