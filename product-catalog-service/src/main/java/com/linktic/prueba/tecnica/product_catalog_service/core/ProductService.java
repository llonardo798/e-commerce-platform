package com.linktic.prueba.tecnica.product_catalog_service.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linktic.prueba.tecnica.product_catalog_service.core.impl.ProductImpl;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.ProductDto;
import com.linktic.prueba.tecnica.product_catalog_service.model.entities.Product;
import com.linktic.prueba.tecnica.product_catalog_service.repository.ProductRepository;

@Service
public class ProductService implements ProductImpl {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::toDto).toList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ProductDto getProductById(int id) {
		return productRepository.getById(id).toDto();
	}

	@Override
	public ProductDto createProduct(ProductDto product) {
		return productRepository.save(product.toEntity()).toDto();
	}

	@Override
	public ProductDto updateProduct(ProductDto product) {
		return productRepository.save(product.toEntity()).toDto();
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public void deleteProduct(int id) {
		productRepository.updateActiveFalse(id);
	}

	@Override
	public List<ProductDto> getAllProductsActive() {
		List<Product> products = productRepository.findAllByActiveTrue();
		return products.stream().map(Product::toDto).toList();
	}

	@Override
	public List<InventoryResponse> veriftStock(List<InventoryResponse> products) {
		List<Integer> productIds = products.stream()
				.map(InventoryResponse::getSku)
				.toList();
		return productRepository.findProductStocks(productIds);
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public Boolean removeStock(List<InventoryResponse> products) {
		List<Product> newStockProducts = new ArrayList<>();
		products.forEach(product -> {
			Product p = this.productRepository.findBySku(product.getSku()).get(0);
			if (p.getStock() >= product.getStock()) {
				p.setStock(p.getStock() - product.getStock());
				newStockProducts.add(p);
			} else {
				throw new RuntimeException("No hay suficiente stock para el producto con sku: " + p.getSku());
			}
		});

		if (newStockProducts.isEmpty() || newStockProducts.size() != products.size()) {
			return false;
		}
		newStockProducts.forEach(this.productRepository::save);
		return true;
	}

}
