package com.ecommerce.ecommerce.exceptions;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message){
        super(message);
    }
}
