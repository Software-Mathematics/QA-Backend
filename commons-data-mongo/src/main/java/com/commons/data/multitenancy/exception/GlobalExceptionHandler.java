//package com.commons.data.multitenancy.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(TenantNotFoundException.class)
//    public ResponseEntity<?> handleTenantNotFoundException(TenantNotFoundException e) {
//        // Log the exception or handle it as needed
//
//        // Create a response entity with a meaningful message and HTTP status
//        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//}
//
