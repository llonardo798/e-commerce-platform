package com.linktic.prueba.tecnica.order_management_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linktic.prueba.tecnica.order_management_service.core.OrderService;
import com.linktic.prueba.tecnica.order_management_service.core.impl.OrderImpl;
import com.linktic.prueba.tecnica.order_management_service.model.dto.Inventory;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderDto;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderItemDto;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final OrderImpl orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		try {
			return ResponseEntity.ok(orderService.getAllOrders());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(orderService.getOrderById(id));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@RequestBody Inventory item) {
		return ResponseEntity.ok(orderService.createOrder(item));
	}

	@PutMapping
	public ResponseEntity<Boolean> addItem(@RequestBody OrderItemDto orderItem) {
		try {
			return ResponseEntity.ok(orderService.addItem(orderItem));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("orderInPaymentProcess/{orderId}")
	public ResponseEntity<Boolean> orderInPaymentProcess(@PathVariable int orderId) {
		return ResponseEntity.ok(orderService.blockOrderAndProducts(orderId));
	}
	

	@PostMapping("/paid/{orderId}")
	public ResponseEntity<Boolean> orderPaid(@PathVariable int orderId) {
		return ResponseEntity.ok(orderService.orderPaid(orderId));
	}
}
