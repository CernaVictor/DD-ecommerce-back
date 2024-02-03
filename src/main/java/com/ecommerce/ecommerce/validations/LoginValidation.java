package com.ecommerce.ecommerce.validations;

import com.ecommerce.ecommerce.dtos.LoginDTO;
import com.ecommerce.ecommerce.exceptions.EmptyField;
import org.springframework.stereotype.Component;

@Component
public class LoginValidation {
    public void loginValidation (final LoginDTO loginDTO) {
        checkEmptyFields(loginDTO);
    }
    public void checkEmptyFields (LoginDTO loginDTO) {
        if(loginDTO.getEmail().isEmpty() || loginDTO.getPassword().isEmpty()) {
            throw new EmptyField("Empty fields!");
        }
    }
}
