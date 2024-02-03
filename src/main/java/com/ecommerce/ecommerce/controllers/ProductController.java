package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dtos.ProductDTO;
import com.ecommerce.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping("/product")
//    public ResponseEntity<ProductDTO> createProduct (@RequestBody ProductDTO productDTO){
//        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
//    }
}