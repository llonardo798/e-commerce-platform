package com.linktic.prueba.tecnica.product_catalog_service.model.dto;

import com.linktic.prueba.tecnica.product_catalog_service.model.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private int id;

    private String name;

    private String description;

    private int price;

    private int stock;

    private String image;

    private int sku;

    private Boolean active;
    
    public Product toEntity() {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .stock(this.stock)
                .image(this.image)
                .sku(this.sku)
                .active(this.active)
                .build();
    }
}
