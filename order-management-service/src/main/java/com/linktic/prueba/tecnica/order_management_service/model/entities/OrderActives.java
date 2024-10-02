package com.linktic.prueba.tecnica.order_management_service.model.entities;

import java.util.List;

import com.linktic.prueba.tecnica.order_management_service.model.audit.AuditableEntity;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDERS_ACTIVES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderActives extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ORDER_NUMBER", nullable = false, length = 100)
    private String orderNumber;
    
    @Column(name = "STATUS", nullable = false)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public OrderDto toDto() {
        return OrderDto.builder()
                .id(this.id)
                .orderNumber(this.orderNumber)
                .status(this.status)
                .orderItems((this.orderItems == null) ? null : this.orderItems.stream().map(OrderItem::toDto).toList())
                .build();
    }
}
