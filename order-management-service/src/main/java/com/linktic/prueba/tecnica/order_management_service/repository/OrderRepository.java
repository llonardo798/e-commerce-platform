package com.linktic.prueba.tecnica.order_management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.linktic.prueba.tecnica.order_management_service.model.entities.OrderActives;

public interface OrderRepository extends JpaRepository<OrderActives, Integer> {

	@Query(value = "SELECT oi.quantity FROM OrderItem oi, OrderActives oa WHERE oa.status = 'PROCESS-PAID' and oi.order.id = oa.id and oi.sku =:sku ")
	Integer separatedProducts(@Param("sku") int sku);

//	List<OrderActives> finByOrderNumber(String orderNumber);

	@Query(value = "UPDATE OrderActives oa SET oa.status = 'PROCESS-PAYMENT' WHERE oa.id =:orderId")
	@Modifying
	void updateStatuspRrocessPayment(int orderId);
	

	@Query(value = "UPDATE OrderActives oa SET oa.status = 'PAID' WHERE oa.id =:orderId")
	@Modifying
	void updateStatusPaid(int orderId);
}
