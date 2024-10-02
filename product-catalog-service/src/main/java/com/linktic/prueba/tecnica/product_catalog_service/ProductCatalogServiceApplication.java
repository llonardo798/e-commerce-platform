package com.linktic.prueba.tecnica.product_catalog_service;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogServiceApplication {

	public static void main(String[] args) {
		
			  
			  List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
			  
			  int sum = list.stream()
			  
			                .map(n -> n * 2)
			  
			                .reduce(0, (a, b) -> a + b);
			  
			  System.out.println(sum);
			  
		
		
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}

}
