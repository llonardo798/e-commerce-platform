package com.linktic.prueba.tecnica.product_catalog_service.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.linktic.prueba.tecnica.product_catalog_service.core.ProductService;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse;
import com.linktic.prueba.tecnica.product_catalog_service.model.entities.Product;
import com.linktic.prueba.tecnica.product_catalog_service.repository.ProductRepository;

class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private List<InventoryResponse> inventorys;

	private List<Product> products;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		products = Arrays.asList(Product.builder().id(1).name("Test Product").description("Test Description").price(100)
				.stock(10).image("test_image.jpg").sku(12345).active(true).build());

		inventorys = Arrays.asList(InventoryResponse.builder().sku(12345).stock(5).build());

	}

	@Test
	void testRemoveStock() {

		when(productRepository.findBySku(12345)).thenReturn(this.products);

		products.get(0).setStock(products.get(0).getStock() - inventorys.get(0).getStock());
		when(productRepository.save(this.products.get(0))).thenReturn(this.products.get(0));
		
		assertEquals(Boolean.TRUE, productService.removeStock(this.inventorys));
		
	}
	

	@Test
	void testRemoveStockException() {

		when(productRepository.findBySku(12345)).thenReturn(this.products);

		products.get(0).setStock(products.get(0).getStock() - (products.get(0).getStock()+1));
		
		// Verificar que se lanza InsufficientStockException
		RuntimeException exception = assertThrowsExactly(RuntimeException.class, () -> {
            productService.removeStock(this.inventorys);
        });
        assertEquals("No hay suficiente stock para el producto con sku: " + this.products.get(0).getSku(), exception.getMessage());
		
	}
}
