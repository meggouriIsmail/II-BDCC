package com.meggouri.customerservice.repositories;

import com.meggouri.customerservice.enteties.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
