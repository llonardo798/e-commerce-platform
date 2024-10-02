package com.linktic.prueba.tecnica.order_management_service.exception;

import java.util.UUID;

public class GetOrderByIdException extends RuntimeException {
	private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();
			
    public GetOrderByIdException(int id) {
        super("Order with id " + id + " not found");
    }
}