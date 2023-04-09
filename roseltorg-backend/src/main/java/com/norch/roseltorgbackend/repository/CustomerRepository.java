package com.norch.roseltorgbackend.repository;

import com.norch.roseltorgbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
