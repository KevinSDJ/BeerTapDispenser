package com.api.beerdispenser.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFound extends ResponseStatusException {

    public NotFound(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
        
    }
    
}
