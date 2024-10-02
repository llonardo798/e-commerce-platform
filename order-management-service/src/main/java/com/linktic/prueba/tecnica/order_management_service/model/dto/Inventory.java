package com.linktic.prueba.tecnica.order_management_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

	private int sku;
	
    private int stock;
    
    private int price;
    
}
