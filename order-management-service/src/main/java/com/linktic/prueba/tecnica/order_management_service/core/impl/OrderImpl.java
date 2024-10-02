package com.linktic.prueba.tecnica.order_management_service.core.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.linktic.prueba.tecnica.order_management_service.model.dto.Inventory;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderDto;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderItemDto;

public interface OrderImpl {

    public List<OrderDto> getAllOrders();

    public OrderDto getOrderById(int id);

    public OrderDto createOrder(Inventory item);

    public boolean addItem(@RequestBody OrderItemDto orderItem);
    
	public boolean blockOrderAndProducts(int orderId);
	
	public boolean orderPaid (int orderId);

}
