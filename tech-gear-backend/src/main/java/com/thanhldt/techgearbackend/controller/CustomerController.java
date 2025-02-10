package com.thanhldt.techgearbackend.controller;

import com.thanhldt.techgearbackend.model.Customer;
import com.thanhldt.techgearbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.getCustomerById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.customerService.getCustomerByUsername(username));
    }

    @PostMapping("/")
    public ResponseEntity<String> addCustomer(@RequestBody Customer newCustomer) {
        this.customerService.addCustomer(newCustomer);
        return ResponseEntity.ok("Add success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer updatingCustomer) {
        this.customerService.updateCustomer(id, updatingCustomer);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.ok("Delete success!");
    }

}
