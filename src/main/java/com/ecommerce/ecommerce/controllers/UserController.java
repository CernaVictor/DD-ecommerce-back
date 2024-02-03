package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dtos.AuthUserDTO;
import com.ecommerce.ecommerce.dtos.LoginDTO;
import com.ecommerce.ecommerce.dtos.RegisterDTO;
import com.ecommerce.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser (@RequestBody RegisterDTO registerDTO){
        return new ResponseEntity<>(userService.registerUser(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserDTO> userLogin (@RequestBody final LoginDTO loginDTO) {
        return new ResponseEntity<>(userService.loginUser(loginDTO),HttpStatus.OK);
    }

    @PutMapping("/forgot-password")
    ResponseEntity<String> forgotPassword(@RequestParam String email){
        return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
    }

    @PutMapping("/set-password")
    ResponseEntity<String> setPassword(@RequestParam String token, @RequestParam String newPassword){
        return new ResponseEntity<>(userService.setPassword(token, newPassword), HttpStatus.OK);
    }
}
