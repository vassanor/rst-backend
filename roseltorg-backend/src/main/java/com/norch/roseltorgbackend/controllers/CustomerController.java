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
@CrossOrigin() // open for all ports
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

    @PostMapping("/customer")
    public ResponseEntity<Customer> newCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerRepository
                .save(Customer.builder()
                        .inn(customer.getInn())
                        .procedure_qty(customer.getProcedure_qty())
                        .win_part(customer.getWin_part())
                        .registration_date(customer.getRegistration_date())
                        .license_activity_type(customer.getLicense_activity_type())
                        .avg_staff_qty(customer.getAvg_staff_qty())
                        .msp_type(customer.getMsp_type())
                        .msp_category(customer.getMsp_category())
                        .life_time(customer.getLife_time())
                        .build());
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {

        //check if employee exist in database
        Customer newCustomer = getCustomer(id);
        if (newCustomer != null) {
            newCustomer.setInn((customer.getInn()));
            newCustomer.setLicense_activity_type(customer.getLicense_activity_type());
            newCustomer.setMsp_type(customer.getMsp_type());
            newCustomer.setRegistration_date(customer.getRegistration_date());
            newCustomer.setProcedure_qty(customer.getProcedure_qty());
            newCustomer.setMsp_category(customer.getMsp_category());
            newCustomer.setAvg_staff_qty(customer.getAvg_staff_qty());
            newCustomer.setLife_time(customer.getLife_time());
            newCustomer.setWin_part(customer.getWin_part());
            return new ResponseEntity<>(customerRepository.save(newCustomer), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable("id") long id) {
        try {
            //check if employee exist in database
            Customer customer = getCustomer(id);

            if (customer != null) {
                customerRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        try {
            customerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Customer getCustomer(long id) {
        Optional<Customer> customerObj = customerRepository.findById(id);

        return customerObj.orElse(null);
    }

}

