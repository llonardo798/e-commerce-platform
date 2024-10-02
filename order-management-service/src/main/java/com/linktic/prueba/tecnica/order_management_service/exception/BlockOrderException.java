package com.linktic.prueba.tecnica.order_management_service.exception;

import java.util.UUID;

public class BlockOrderException extends RuntimeException {

    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public BlockOrderException(int id, Exception e) {
        super("Error update by bloc Order with id " + id + ". Exceprion: " + e.toString());
    }

    public BlockOrderException(Exception e) {
        super("Error consult Items of Order. Exceprion: " + e.getMessage());

    }
    
    public BlockOrderException(Exception e, String message) {
    	super(message + " Exception: " + e.toString());
    }
    

    public BlockOrderException(String message) {
    	super(message);
    }

}
