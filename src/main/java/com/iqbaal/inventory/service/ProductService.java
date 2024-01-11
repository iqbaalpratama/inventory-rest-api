package com.iqbaal.inventory.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.iqbaal.inventory.dto.request.ProductRequest;
import com.iqbaal.inventory.entity.Product;
import com.iqbaal.inventory.exception.ProductNotFoundException;
import com.iqbaal.inventory.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product create(ProductRequest request){
        Product createdProduct = new Product();
        createdProduct.setName(request.getName());
        createdProduct.setDescription(request.getDescription());
        createdProduct.setPrice(request.getPrice());
        createdProduct.setStock(request.getStock());
        productRepository.save(createdProduct);
        return createdProduct;
    }

    public Product edit(ProductRequest request, Integer id) throws ProductNotFoundException{
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: "+id+" is not found"));
        if (Objects.nonNull(request.getName())) {
            currentProduct.setName(request.getName());
        }
        if (Objects.nonNull(request.getPrice())) {
            currentProduct.setPrice(request.getPrice());
        }
        if (Objects.nonNull(request.getDescription())) {
            currentProduct.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getStock())) {
            currentProduct.setStock(request.getStock());
        }
        productRepository.save(currentProduct);
        return currentProduct;
    }

    public List<Product> getAll(){
        List<Product> result = productRepository.findAll();
        return result;
    }

    public Product getOne(Integer id) throws ProductNotFoundException {
        Product result = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: "+id+" is not found"));
        return result;
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public void deleteOne(Integer id) throws ProductNotFoundException{
        Product result = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: "+id+" is not found"));
        productRepository.delete(result);
    }

    public List<Product> getByName(String keyword){
        List<Product> result = productRepository.findByNameContainingIgnoreCase(keyword);
        return result;
    }
}
