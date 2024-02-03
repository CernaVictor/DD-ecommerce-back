package com.ecommerce.ecommerce.dtos;

import com.ecommerce.ecommerce.entities.Photo;
import java.util.List;

public class ProductDTO {
    private String name;
    private String description;
    private Long stock;
    private Double price;
    private List<Photo> image;
}
