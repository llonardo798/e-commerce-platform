package com.linktic.prueba.tecnica.order_management_service.core;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linktic.prueba.tecnica.order_management_service.core.impl.OrderImpl;
import com.linktic.prueba.tecnica.order_management_service.exception.GetOrderByIdException;
import com.linktic.prueba.tecnica.order_management_service.exception.OrderPaiException;
import com.linktic.prueba.tecnica.order_management_service.exception.BlockOrderException;
import com.linktic.prueba.tecnica.order_management_service.exception.CreateOrderException;
import com.linktic.prueba.tecnica.order_management_service.model.dto.Inventory;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderDto;
import com.linktic.prueba.tecnica.order_management_service.model.dto.OrderItemDto;
import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderActives;
import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderItem;
import com.linktic.prueba.tecnica.order_management_service.repository.OrderItemRepository;
import com.linktic.prueba.tecnica.order_management_service.repository.OrderRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Service
public class OrderService implements OrderImpl {

	private final OrderRepository orderRepository;

	private final OrderItemRepository itemRepository;

	private final Builder webClientBuilder;

	@Value("${api.url.products}")
	private String urlProducts;

	@Autowired
	public OrderService(OrderRepository orderRepository, Builder webClientBuilder, OrderItemRepository itemRepository) {
		this.orderRepository = orderRepository;
		this.webClientBuilder = webClientBuilder;
		this.itemRepository = itemRepository;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderActives> orders = orderRepository.findAll();
		return orders.stream().map(OrderActives::toDto).toList();
	}

	@Override
	public OrderDto getOrderById(int id) {
		OrderActives order = orderRepository.findById(id).orElseThrow(() -> new GetOrderByIdException(id));
		return order.toDto();
	}

	@Override
	public OrderDto createOrder(Inventory item) {
		int price = validateStock(item);
		OrderActives newOrder = OrderActives.builder().orderNumber(UUID.randomUUID().toString()).status("PENDING")
				.build();
		OrderActives order;
		try {
			order = orderRepository.save(newOrder);
		} catch (Exception e) {
			throw new CreateOrderException("Orden", e);
		}
		OrderItem addItem = OrderItem.builder().quantity(item.getStock()).order(order).sku(item.getSku()).price(price)
				.build();
		try {
			order.setOrderItems(Collections.singletonList(itemRepository.save(addItem)));
		} catch (Exception e) {
			this.deleteOrder(order);
			throw new CreateOrderException("OrdenItem", e);
		}
		return order.toDto();

	}

	@Override
	public boolean addItem(@RequestBody OrderItemDto orderItem) {
		Inventory item = Inventory.builder().sku(orderItem.getSku()).stock(orderItem.getQuantity()).build();
		orderItem.setPrice(validateStock(item));
		try {
			itemRepository.save(orderItem.toEntity());
			return true;
		} catch (Exception e) {
			throw new CreateOrderException("OrdenItem", e);
		}

	}

	private int validateStock(Inventory item) {
		List<Inventory> result = apiInSotckProduct(Collections.singletonList(item));

		Integer separateQuantity = orderRepository.separatedProducts(item.getSku());
		separateQuantity = (separateQuantity == null) ? 0 : separateQuantity;

		if (result != null && (result.get(0).getStock() - separateQuantity) > item.getStock()) {
			return result.get(0).getPrice();
		} else {
			throw new CreateOrderException(item);
		}
	}

	private List<Inventory> apiInSotckProduct(List<Inventory> items) {
		try {
			System.out.println("urlProducts: " + urlProducts);
			return this.webClientBuilder.build().post().uri(urlProducts + "/inStock").bodyValue(items).retrieve()
					.bodyToFlux(Inventory.class).collectList().block();

		} catch (Exception e) {
			throw new CreateOrderException(e, items.stream().mapToInt(Inventory::getSku).toString());
		}
	}

	private void deleteOrder(OrderActives order) {
		orderRepository.delete(order);
	}

	@Transactional
	@Override
	public boolean blockOrderAndProducts(int orderId) {
		List<OrderItem> items;
		try {
			items = this.itemRepository.findByOrder(OrderActives.builder().id(orderId).build());
		} catch (Exception e) {
			throw new BlockOrderException(e);
		}

		List<Inventory> itemsInventory = items.stream().map((item) -> {
			return Inventory.builder().sku(item.getSku()).stock(item.getQuantity()).build();
		}).toList();

		itemsInventory = this.apiInSotckProduct(itemsInventory);
		
		try {
			this.orderRepository.updateStatuspRrocessPayment(orderId);
		} catch (Exception e) {
			throw new BlockOrderException(orderId, e);
		}

		return true;
	}

	@Transactional
	@Override
	public boolean orderPaid(int orderId) {
		List<OrderItem> items;
		try {
			items = this.itemRepository.findByOrder(OrderActives.builder().id(orderId).build());
		} catch (Exception e) {
			throw new OrderPaiException("Error consult Items of Order" + orderId, e);
		}

		List<Inventory> itemsInventory = items.stream()
				.map(item -> Inventory.builder().sku(item.getSku()).stock(item.getQuantity()).build()).toList();

		try {
			boolean stock = this.webClientBuilder.build().post().uri(urlProducts + "/removeStock")
					.bodyValue(itemsInventory)
					.retrieve().bodyToMono(Boolean.class).block();

			if (Boolean.FALSE.equals(stock)) {
				throw new OrderPaiException("Failed to remove inventory items for order: " + orderId);
			}
		} catch (Exception e) {
			throw new OrderPaiException("Error consumer api remove products", e);
		}

		try {
			this.orderRepository.updateStatusPaid(orderId);
			return true;
		} catch (Exception e) {
			throw new OrderPaiException("Error update status order", e);
		}

	}

}
