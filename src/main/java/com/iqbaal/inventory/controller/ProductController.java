package com.iqbaal.inventory.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iqbaal.inventory.dto.request.ProductRequest;
import com.iqbaal.inventory.dto.response.APIResponse;
import com.iqbaal.inventory.entity.Product;
import com.iqbaal.inventory.exception.ProductNotFoundException;
import com.iqbaal.inventory.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/products")
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER')")
    public ResponseEntity<APIResponse<List<Product>>> getAll(@RequestParam(required = false) String name){
        if (!Objects.isNull(name)) {
            return new ResponseEntity<APIResponse<List<Product>>>(new APIResponse<List<Product>>("Success", 200, "Success get all products by keyword: "+name+" in name product", productService.getByName(name)), HttpStatus.OK);
        }
        return new ResponseEntity<APIResponse<List<Product>>>(new APIResponse<List<Product>>("Success", 200, "Success get all products", productService.getAll()), HttpStatus.OK); 
    }

        
    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER')")
    public ResponseEntity<APIResponse<Product>> getById(@PathVariable Integer id) throws ProductNotFoundException{
        return new ResponseEntity<APIResponse<Product>>(new APIResponse<Product>("Success", 200, "Success get product with id: "+id, productService.getOne(id)),HttpStatus.OK);
    }


    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<Product>> createproduct(@RequestBody @Valid ProductRequest request){
        return new ResponseEntity<APIResponse<Product>>(new APIResponse<Product>("Success", 201, "Success create new product", productService.create(request)), HttpStatus.CREATED);
    }

    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<Object>> deleteAllproduct() {
        productService.deleteAll();
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Success", 200, "Success delete all products", null), HttpStatus.OK);
    } 

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<Object>> deleteproductById(@PathVariable Integer id) throws ProductNotFoundException {
        productService.deleteOne(id);
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Success", 200, "Success delete product with id: "+id, null), HttpStatus.OK);
    }
    
    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse<Product>> updateproduct(@PathVariable Integer id, @RequestBody ProductRequest ProductRequest) throws ProductNotFoundException{
        return new ResponseEntity<APIResponse<Product>>(new APIResponse<Product>("Success", 201, "Success update product with id: "+id, productService.edit(ProductRequest, id)), HttpStatus.OK);
    } 
}

