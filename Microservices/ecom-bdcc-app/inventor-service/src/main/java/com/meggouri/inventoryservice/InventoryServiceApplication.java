package com.meggouri.inventoryservice;

import com.meggouri.inventoryservice.entities.Product;
import com.meggouri.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder()
							.id(UUID.randomUUID().toString())
							.name("mouse")
							.price(120)
							.quantity(10)
					.build());
			productRepository.save(Product.builder()
							.id(UUID.randomUUID().toString())
							.name("Laptop")
							.price(23000)
							.quantity(5)
					.build());
			productRepository.save(Product.builder()
							.id(UUID.randomUUID().toString())
							.name("TV")
							.price(3500)
							.quantity(25)
					.build());
		};
	}

}
