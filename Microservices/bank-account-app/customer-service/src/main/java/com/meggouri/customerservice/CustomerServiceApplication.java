package com.meggouri.customerservice;

import com.meggouri.customerservice.enteties.Customer;
import com.meggouri.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {

			List<Customer> customerList=List.of(
					Customer.builder()
							.firstName("yellow")
							.lastName("hellow")
							.email("yellow@gmail.com")
							.build(),
					Customer.builder()
							.firstName("fizz")
							.lastName("buzz")
							.email("fizz@gmail.com")
							.build()

			);
			customerRepository.saveAll(customerList);
		};
	}
}
