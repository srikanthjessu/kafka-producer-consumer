package com.sri.kafka.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.kafka.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
