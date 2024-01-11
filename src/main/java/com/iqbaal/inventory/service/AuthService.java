package com.iqbaal.inventory.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.iqbaal.inventory.dto.request.LoginUserRequest;
import com.iqbaal.inventory.dto.request.RegisterUserRequest;
import com.iqbaal.inventory.dto.response.TokenResponse;
import com.iqbaal.inventory.dto.response.UserResponse;
import com.iqbaal.inventory.entity.Role;
import com.iqbaal.inventory.entity.User;
import com.iqbaal.inventory.entity.UserInfoDetails;
import com.iqbaal.inventory.entity.utils.EnumRole;
import com.iqbaal.inventory.exception.RequestIsNotValidException;
import com.iqbaal.inventory.exception.UserNotFoundException;
import com.iqbaal.inventory.repository.RoleRepository;
import com.iqbaal.inventory.repository.UserRepository;
import com.iqbaal.inventory.security.jwt.JwtUtils;
import com.iqbaal.inventory.service.utils.UserMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ValidationService validationService;


    @Transactional
    public UserResponse register(RegisterUserRequest registerUserRequest, int role) {
        validationService.validate(registerUserRequest);
        if(userRepository.existsByEmail(registerUserRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }
        registerUserRequest.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        EnumRole currentRole = role == 1 ? EnumRole.ADMIN : EnumRole.CUSTOMER; 
        Set<Role> roles = this.getRoles(currentRole);
        User user = UserMapper.toUser(registerUserRequest, roles);
        userRepository.save(user);
        return UserMapper.fromUser(user);
    }

    @Transactional
    public TokenResponse authenticate(LoginUserRequest loginUserRequest) {
        validationService.validate(loginUserRequest);
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUserRequest.getEmail(),
                loginUserRequest.getPassword()
            )
        );
        User user = userRepository.findByEmail(loginUserRequest.getEmail())
            .orElseThrow();
        UserDetails userDetails = new UserInfoDetails(user);
        var jwtToken = jwtUtils.generateToken(userDetails);
        var refreshToken = jwtUtils.generateRefreshToken(userDetails);
        return TokenResponse.builder()
            .token(jwtToken)
            .expiresIn(30*60*1000)
            .refreshToken(refreshToken)
            .build();
    }

    public TokenResponse refreshToken(HttpServletRequest request) throws IOException, UserNotFoundException, RequestIsNotValidException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new RequestIsNotValidException("Authorization Header Type should be bearer");
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UserNotFoundException("User with email: "+userEmail+" is not found."));
            UserDetails userDetails = new UserInfoDetails(user);
            if (jwtUtils.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtUtils.generateToken(userDetails);
                TokenResponse authResponse = TokenResponse.builder()
                        .token(accessToken)
                        .expiresIn(30*60*100)
                        .refreshToken(refreshToken)
                        .build();
                return authResponse;
            }
        }
        return null;
    }

    private Set<Role> getRoles(EnumRole role){
        Set<Role> roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(role)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        return roles;
    }
}

