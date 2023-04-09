package com.norch.roseltorgbackend.repository;

import com.norch.roseltorgbackend.model.Customer;
import com.norch.roseltorgbackend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
