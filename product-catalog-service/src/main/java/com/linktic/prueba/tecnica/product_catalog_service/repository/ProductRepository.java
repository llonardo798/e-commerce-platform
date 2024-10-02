package com.linktic.prueba.tecnica.product_catalog_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse;
import com.linktic.prueba.tecnica.product_catalog_service.model.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "UPDATE Product pr SET pr.active = false where pr.id =:idPr")
	@Modifying(clearAutomatically = true)
	void updateActiveFalse(@Param("idPr") int id);

	List<Product> findAllByActiveTrue();
	
    @Query("SELECT new com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse(p.sku, p.stock, p.price)  FROM Product p WHERE p.sku IN :productSku")
    List<InventoryResponse> findProductStocks(@Param("productSku") List<Integer> productSku);

    List<Product> findBySku(int sku);
}
