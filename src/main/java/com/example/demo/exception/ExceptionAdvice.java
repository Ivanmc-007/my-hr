package com.example.demo.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
