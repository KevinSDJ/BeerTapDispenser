package com.api.beerdispenser.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalError extends ResponseStatusException {

    public InternalError(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
    
}
