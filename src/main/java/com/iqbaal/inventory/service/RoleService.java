package com.iqbaal.inventory.service;

import org.springframework.stereotype.Service;

import com.iqbaal.inventory.entity.Role;
import com.iqbaal.inventory.entity.utils.EnumRole;
import com.iqbaal.inventory.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role create(EnumRole role){
        Role newRole = new Role(role);
        roleRepository.save(newRole);
        return newRole;
    }
}
