package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"product\"")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany
    @JoinColumn(name = "image")
    private List <Photo> image;
}