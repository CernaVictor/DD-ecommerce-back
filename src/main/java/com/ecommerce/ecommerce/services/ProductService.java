package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dtos.ProductDTO;
import com.ecommerce.ecommerce.entities.Photo;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.mappers.PhotoMapper;
import com.ecommerce.ecommerce.mappers.ProductMapper;
import com.ecommerce.ecommerce.repositories.PhotoRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PhotoRepository photoRepository;

    private final ProductMapper productMapper;

    private final PhotoMapper photoMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, PhotoRepository photoRepository, ProductMapper productMapper, PhotoMapper photoMapper) {
        this.productRepository = productRepository;
        this.photoRepository = photoRepository;
        this.productMapper = productMapper;
        this.photoMapper = photoMapper;
    }

//    public Product createProduct(ProductDTO productDTO) {
//
//        Product newProduct = productMapper.toProduct(productDTO);
//
//        return newProduct;
//    }
}
