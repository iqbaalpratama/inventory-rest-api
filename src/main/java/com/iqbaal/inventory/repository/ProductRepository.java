package com.iqbaal.inventory.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbaal.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
    Optional<Product> findByName(String name);
    Optional<Product> findById(Integer id);
}
