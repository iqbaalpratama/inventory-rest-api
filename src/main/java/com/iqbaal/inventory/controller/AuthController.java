package com.iqbaal.inventory.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqbaal.inventory.dto.request.LoginUserRequest;
import com.iqbaal.inventory.dto.request.RegisterUserRequest;
import com.iqbaal.inventory.dto.response.APIResponse;
import com.iqbaal.inventory.dto.response.TokenResponse;
import com.iqbaal.inventory.dto.response.UserResponse;
import com.iqbaal.inventory.exception.RequestIsNotValidException;
import com.iqbaal.inventory.exception.UserNotFoundException;
import com.iqbaal.inventory.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "/register-admin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<UserResponse>> registerAdmin(@RequestBody RegisterUserRequest registerUserRequest){
        UserResponse userResponse = authService.register(registerUserRequest, 1);
        return new ResponseEntity<APIResponse<UserResponse>>(new APIResponse<UserResponse>("Success", 201, "Success register new admin", userResponse), HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/register-customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<UserResponse>> registerCustomer(@RequestBody RegisterUserRequest registerUserRequest){
        UserResponse userResponse = authService.register(registerUserRequest, 2);
        return new ResponseEntity<APIResponse<UserResponse>>(new APIResponse<UserResponse>("Success", 201, "Success register new customer", userResponse), HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<TokenResponse>> login(@RequestBody LoginUserRequest loginUserRequest, HttpServletResponse response){
        TokenResponse tokenResponse = authService.authenticate(loginUserRequest);
        return new ResponseEntity<APIResponse<TokenResponse>>(new APIResponse<TokenResponse>("Success", 200, "Login success", tokenResponse), HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<APIResponse<TokenResponse>> refreshToken(HttpServletRequest request) throws io.jsonwebtoken.io.IOException, UserNotFoundException, RequestIsNotValidException {
        TokenResponse tokenResponse = authService.refreshToken(request);
        return new ResponseEntity<APIResponse<TokenResponse>>(new APIResponse<TokenResponse>("Success", 200, "Get refresh-token success", tokenResponse), HttpStatus.CREATED);
    }
}

