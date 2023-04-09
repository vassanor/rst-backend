package com.norch.roseltorgbackend.controllers;


import com.norch.roseltorgbackend.model.Supplier;
import com.norch.roseltorgbackend.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000") //open for specific port
@RestController
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        try {
            return new ResponseEntity<>(supplierRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") long id) {
        try {
            //check if employee exist in database
            Supplier supplier = getSupplier(id);

            if (supplier != null) {
                return new ResponseEntity<>(supplier, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Supplier getSupplier(long id) {
        Optional<Supplier> supplierObj = supplierRepository.findById(id);

        return supplierObj.orElse(null);
    }

}

