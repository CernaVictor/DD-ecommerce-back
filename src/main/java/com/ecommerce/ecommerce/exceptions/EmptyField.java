package com.ecommerce.ecommerce.exceptions;

public class EmptyField extends RuntimeException{
    public EmptyField(String message){
        super(message);
    }
}
