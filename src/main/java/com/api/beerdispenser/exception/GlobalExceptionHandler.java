package com.api.beerdispenser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= BadRequest.class)
    public ResponseEntity<String> handleBadRequest( BadRequest badRequest){
        return new ResponseEntity<String>(badRequest.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=NotFound.class)
    public ResponseEntity<String> handleNotFound( NotFound notFound){
        return new ResponseEntity<String>(notFound.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=InternalServerError.class)
    public ResponseEntity<String> handleServerError( InternalServerError internalServerError){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value= ConflictException.class)
    public ResponseEntity<String> handleBadRequest( ConflictException conflictException){
        return new ResponseEntity<String>(conflictException.getMessage(),HttpStatus.BAD_REQUEST);
    }
   
}
