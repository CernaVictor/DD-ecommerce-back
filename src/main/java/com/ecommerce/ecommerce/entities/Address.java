package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "\"address\"")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "street_name", length = 256, nullable = false)
    private String streetName;

    @Column(name = "property_number", length = 256, nullable = false)
    private String propertyNumber;

    @Column(name = "postal_code", length = 256, nullable = false)
    private String postalCode;

    @Column(name = "country", length = 256, nullable = false)
    private String country;

    @Column(name= "county", length = 256, nullable = false)
    private String county;

    @Column(name= "city", length = 256, nullable = false)
    private String city;
}