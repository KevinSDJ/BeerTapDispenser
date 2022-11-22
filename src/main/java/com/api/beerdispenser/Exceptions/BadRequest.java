package com.api.beerdispenser.Exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

public class BadRequest extends ResponseStatusException {

    public BadRequest(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);

    }
    
}
