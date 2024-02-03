package com.ecommerce.ecommerce.validations;

import com.ecommerce.ecommerce.dtos.RegisterDTO;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.exceptions.EmailAlreadyExists;
import com.ecommerce.ecommerce.exceptions.EmptyField;
import com.ecommerce.ecommerce.exceptions.IncorrectPassword;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidation {
    private final UserRepository userRepository;

    public RegisterValidation (final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void registerValidation (final RegisterDTO registerDTO) {
        checkEmptyFields(registerDTO);
        checkEmail(registerDTO);
        checkPassword(registerDTO.getUser().getPassword());

    }
    public void checkEmptyFields (final RegisterDTO registerDTO) {
        if(registerDTO.getUser().getFirstName().isEmpty() || registerDTO.getUser().getLastName().isEmpty() ||
                registerDTO.getUser().getEmail().isEmpty() || registerDTO.getUser().getPassword().isEmpty()) {
            throw new EmptyField("Empty fields!");
        }
    }

    public void checkEmail (final RegisterDTO registerDTO)  {
        for(User user : userRepository.findAll()){
            if(user.getEmail().equals(registerDTO.getUser().getEmail()))
                throw new EmailAlreadyExists("Email already exists!");
        }

    }
    public void checkPassword (final String password) {
        if(password.length()<3)
            throw new IncorrectPassword("Password should have at least 3 characters!");
    }
}
