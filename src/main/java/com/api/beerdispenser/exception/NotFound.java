package com.api.beerdispenser.exception;

public class NotFound extends RuntimeException {
    
    public NotFound(String message){
        super(message);
    }
}
