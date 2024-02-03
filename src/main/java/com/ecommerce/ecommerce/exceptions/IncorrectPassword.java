package com.ecommerce.ecommerce.exceptions;

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword (String message) {
        super(message);
    }
}
