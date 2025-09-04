package com.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
