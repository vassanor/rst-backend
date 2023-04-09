package com.norch.roseltorgbackend.controllers;


import com.norch.roseltorgbackend.model.Customer;
import com.norch.roseltorgbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000") //open for specific port
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        try {
            return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        try {
            //check if employee exist in database
            Customer customer = getCustomer(id);

            if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    private Customer getCustomer(long id) {
        Optional<Customer> customerObj = customerRepository.findById(id);

        return customerObj.orElse(null);
    }

}

