package com.dakshit.OnlineBidding.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> duplicateUserException(DuplicateUserException duplicateUserException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateUserException.getMessage());
    }


}
