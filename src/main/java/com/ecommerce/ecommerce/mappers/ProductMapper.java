package com.ecommerce.ecommerce.mappers;

import com.ecommerce.ecommerce.dtos.ProductDTO;
import com.ecommerce.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    ProductDTO toProductDTO(Product product);
}