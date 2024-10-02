package com.linktic.prueba.tecnica.product_catalog_service.model.entities;

import com.linktic.prueba.tecnica.product_catalog_service.model.audit.AuditableEntity;
import com.linktic.prueba.tecnica.product_catalog_service.model.dto.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends AuditableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 350)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "STOCK", nullable = false)
    private int stock;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @Column(name = "SKU", nullable = false)
    private int sku;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    public ProductDto toDto() {
        return ProductDto.builder()
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
