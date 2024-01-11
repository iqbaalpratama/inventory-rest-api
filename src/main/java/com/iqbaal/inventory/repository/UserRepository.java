package com.iqbaal.inventory.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbaal.inventory.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}