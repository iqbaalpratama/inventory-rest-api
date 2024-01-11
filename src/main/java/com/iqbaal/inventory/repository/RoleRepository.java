package com.iqbaal.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbaal.inventory.entity.Role;
import com.iqbaal.inventory.entity.utils.EnumRole;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    boolean existsByName(EnumRole name);
    Optional<Role> findByName(EnumRole name);
}
