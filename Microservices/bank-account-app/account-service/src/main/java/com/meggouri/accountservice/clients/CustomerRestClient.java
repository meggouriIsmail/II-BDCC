package com.meggouri.accountservice.clients;

import com.meggouri.accountservice.modeles.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id, Exception exception) {
        return Customer.builder()
                .email("Not Available")
                .firstName("Not Available")
                .lastName("Not Available")
                .id(id)
                .build();
    }

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomers")
    List<Customer> allCustomers();

    default List<Customer> getDefaultCustomers(Exception exception) {
        return List.of();
    }
}
