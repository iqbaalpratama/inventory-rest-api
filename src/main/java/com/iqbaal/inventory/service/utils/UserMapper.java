package com.iqbaal.inventory.service.utils;


import java.time.ZonedDateTime;
import java.util.Set;

import com.iqbaal.inventory.dto.request.RegisterUserRequest;
import com.iqbaal.inventory.dto.response.UserResponse;
import com.iqbaal.inventory.entity.Role;
import com.iqbaal.inventory.entity.User;

public class UserMapper {

    public static User toUser(RegisterUserRequest registerUserRequest, Set<Role> roles){
        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setName(registerUserRequest.getName());
        user.setPassword(registerUserRequest.getPassword());
        user.setCreatedDate(ZonedDateTime.now());
        user.setRoles(roles);
        return user;
    }

    public static UserResponse fromUser(User user){
        return UserResponse.builder()
            .email(user.getEmail())
            .name(user.getName())
            .createdDate(user.getCreatedDate())
            .build();
    }
}

