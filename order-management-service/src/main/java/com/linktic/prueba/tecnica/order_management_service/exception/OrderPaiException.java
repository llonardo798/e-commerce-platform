package com.linktic.prueba.tecnica.order_management_service.exception;

public class OrderPaiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderPaiException(String message, Exception e) {
        super(message + " Exception: " + e.toString());
    }

    
    public OrderPaiException(String message) {
        super(message);
    }


}
