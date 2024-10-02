package com.linktic.prueba.tecnica.order_management_service.model.dto;

import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderActives;
import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private int id;

    private int quantity;

    private int price;

    private int sku;
    
    private int orderId;
    
    public OrderItem toEntity() {
        return OrderItem.builder()
                .id(this.id)
                .quantity(this.quantity)
                .price(this.price)
                .sku(this.sku)
                .order(OrderActives.builder().id(orderId).build())
                .build();
    }
}
