package com.linktic.prueba.tecnica.order_management_service.model.dto;

import java.util.List;

import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderActives;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

	private int id;

    private String orderNumber;

    private String status;
    
    private List<OrderItemDto> orderItems;
    
    public OrderActives toEntity() {
        return OrderActives.builder()
                .id(this.id)
                .status(this.status)
                .orderNumber(this.orderNumber)
                .orderItems((this.orderItems == null) ? null : this.orderItems.stream().map(OrderItemDto::toEntity).toList())
                .build();
    }
    
}
