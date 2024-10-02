package com.linktic.prueba.tecnica.product_catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.linktic.prueba.tecnica.product_catalog_service.model.dto.InventoryResponse;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.ProductDto;
import com.linktic.prueba.tecnica.product_catalog_service.core.ProductService;
import com.linktic.prueba.tecnica.product_catalog_service.core.impl.ProductImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductImpl productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/active")
    public List<ProductDto> getAllProductsActive() {
        return productService.getAllProductsActive();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return productService.createProduct(product);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/inStock")
    public ResponseEntity<List<InventoryResponse>> verifyStock(@RequestBody List<InventoryResponse> products) {
        return ResponseEntity.ok(productService.veriftStock(products));
    }

    @PostMapping("/removeStock")
    public Boolean removeStock(@RequestBody List<InventoryResponse> products) {
        return productService.removeStock(products);
    }

}
