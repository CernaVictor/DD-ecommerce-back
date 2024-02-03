package com.ecommerce.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
    private UserDTO user;
    private AddressDTO deliveryAddress;
    private AddressDTO billingAddress;
}
