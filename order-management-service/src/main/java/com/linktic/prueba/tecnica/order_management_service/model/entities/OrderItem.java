package com.linktic.prueba.tecnica.order_management_service.model.entities;

import com.linktic.prueba.tecnica.order_management_service.model.audit.AuditableEntity;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderItemDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "sku", nullable = false)
    private int sku;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderActives order;

    public OrderItemDto toDto() {
        return OrderItemDto.builder()
                .id(this.id)
                .quantity(this.quantity)
                .price(this.price)
                .sku(this.sku)
                .orderId(order.getId())
                .build();
    }

}
