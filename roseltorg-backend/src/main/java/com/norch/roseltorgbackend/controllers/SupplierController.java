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
@CrossOrigin() // open for all ports
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

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> newSupplier(@RequestBody Supplier supplier) {
        Supplier newSupplier = supplierRepository
                .save(Supplier.builder()
                        .inn(supplier.getInn())
                        .procedure_qty(supplier.getProcedure_qty())
                        .win_part(supplier.getWin_part())
                        .registration_date(supplier.getRegistration_date())
                        .license_activity_type(supplier.getLicense_activity_type())
                        .avg_staff_qty(supplier.getAvg_staff_qty())
                        .msp_type(supplier.getMsp_type())
                        .msp_category(supplier.getMsp_category())
                        .life_time(supplier.getLife_time())
                        .build());
        return new ResponseEntity<>(newSupplier, HttpStatus.OK);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") long id, @RequestBody Supplier supplier) {

        //check if employee exist in database
        Supplier newSupplier = getSupplier(id);
        if (newSupplier != null) {
            newSupplier.setInn((supplier.getInn()));
            newSupplier.setLicense_activity_type(supplier.getLicense_activity_type());
            newSupplier.setMsp_type(supplier.getMsp_type());
            newSupplier.setRegistration_date(supplier.getRegistration_date());
            newSupplier.setProcedure_qty(supplier.getProcedure_qty());
            newSupplier.setMsp_category(supplier.getMsp_category());
            newSupplier.setAvg_staff_qty(supplier.getAvg_staff_qty());
            newSupplier.setLife_time(supplier.getLife_time());
            newSupplier.setWin_part(supplier.getWin_part());
            return new ResponseEntity<>(supplierRepository.save(newSupplier), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<HttpStatus> deleteSupplierById(@PathVariable("id") long id) {
        try {
            //check if employee exist in database
            Supplier supplier = getSupplier(id);

            if (supplier != null) {
                supplierRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/suppliers")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        try {
            supplierRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Supplier getSupplier(long id) {
        Optional<Supplier> supplierObj = supplierRepository.findById(id);

        return supplierObj.orElse(null);
    }

}

