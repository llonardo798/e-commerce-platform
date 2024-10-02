package com.linktic.prueba.tecnica.order_management_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobarlExceptionHandler {

	@ExceptionHandler(GetOrderByIdException.class)
    public ResponseEntity<String> handleOrderNotFoundException(GetOrderByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
