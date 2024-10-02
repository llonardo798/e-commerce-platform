package com.linktic.prueba.tecnica.product_catalog_service.core.impl;

import java.util.List;

import com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.ProductDto;

public interface ProductImpl {
    public List<ProductDto> getAllProducts();

    public ProductDto getProductById(int id);

    public ProductDto createProduct(ProductDto product);

    public ProductDto updateProduct(ProductDto product);

    public void deleteProduct(int id);

    public List<ProductDto> getAllProductsActive();

    public List<InventoryResponse> veriftStock(List<InventoryResponse> products);

    Boolean removeStock(List<InventoryResponse> products);
}
