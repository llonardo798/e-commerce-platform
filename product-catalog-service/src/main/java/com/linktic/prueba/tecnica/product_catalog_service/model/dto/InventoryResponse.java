package com.linktic.prueba.tecnica.product_catalog_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

	private int sku;
	
    private int stock;
    
    private int price;
    
}
