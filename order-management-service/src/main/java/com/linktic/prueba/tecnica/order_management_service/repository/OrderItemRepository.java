package com.linktic.prueba.tecnica.order_management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderActives;
import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderItem;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrder(OrderActives order);

}
