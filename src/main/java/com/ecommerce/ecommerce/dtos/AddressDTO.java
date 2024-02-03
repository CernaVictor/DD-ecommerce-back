package com.ecommerce.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private String streetName;
    private String propertyNumber;
    private Long postalCode;
    private String country;
    private String county;
    private String city;
}
