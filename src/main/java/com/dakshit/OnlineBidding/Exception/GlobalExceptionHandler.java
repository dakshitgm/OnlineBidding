package com.dakshit.OnlineBidding.Exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.core.Ordered;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> duplicateUserException(DuplicateUserException duplicateUserException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateUserException.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFoundException.getMessage());
    }

    @ExceptionHandler(BidNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> bidNotFoundExceptionHandler(BidNotFoundException bidNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bidNotFoundException.getMessage());
    }

    @ExceptionHandler(UnauthorisedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> unauthorisedAccessExceptionHandler(UnauthorisedAccessException unauthorisedAccessException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorisedAccessException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        // You can use a logging framework like SLF4J or log directly to System.out
        // Example: log.error("An error occurred", e);

        // Return an appropriate response to the client
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred. Please try again later."+ e.toString());
    }
}
