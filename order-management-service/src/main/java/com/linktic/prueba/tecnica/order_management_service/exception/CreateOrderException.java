package com.linktic.prueba.tecnica.order_management_service.exception;

import java.util.UUID;

import com.linktic.prueba.tecnica.order_management_service.model.dto.Inventory;

public class CreateOrderException extends RuntimeException {
	private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();
			
    public CreateOrderException(Inventory item) {
        super("There is no stock of " + item.getStock() + " products for product with SKU" + item.getSku());
    }
    
    public CreateOrderException(String type, Exception e) {
        super("Error creating " + type + "in DB, exception: " + e.toString());
    }

    public CreateOrderException(Exception e, String sku) {
        super("Error consuming external service to verify stock of product with SKU: " + sku + ". Exeption: " + e.toString());
    }
}