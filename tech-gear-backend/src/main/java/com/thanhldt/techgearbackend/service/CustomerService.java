package com.thanhldt.techgearbackend.service;

import com.thanhldt.techgearbackend.exception.ResourceNotFoundException;
import com.thanhldt.techgearbackend.model.Cart;
import com.thanhldt.techgearbackend.model.Customer;
import com.thanhldt.techgearbackend.repository.CartRepository;
import com.thanhldt.techgearbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id!"));
    }

    public Customer getCustomerByUsername(String username) {
        return this.customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer username!"));
    }

    public void addCustomer(Customer newCustomer) {
        if(this.customerRepository.existsByUsername(newCustomer.getUsername())) {
            throw new RuntimeException("Customer username already exists!");
        }
        if(!newCustomer.getEmail().isEmpty() && this.customerRepository.existsByEmail(newCustomer.getEmail())) {
            throw new RuntimeException("Customer email already exists!");
        }
        newCustomer.setCart(new Cart());
        newCustomer.getCart().setCustomer(newCustomer);
        newCustomer.getCart().setTotalPrice(new BigDecimal(0));
        newCustomer.getCart().setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newCustomer.getCart().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        newCustomer.setIsAdmin(false);
        newCustomer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newCustomer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.customerRepository.save(newCustomer);
    }

    public void updateCustomer(Long id, Customer updatingCustomer) {
        Customer foundCustomer = this.customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id!"));
        foundCustomer.setPassword(updatingCustomer.getPassword());
        if(!updatingCustomer.getEmail().equals(foundCustomer.getEmail())) {
            if(this.customerRepository.existsByEmail(updatingCustomer.getEmail())) {
                throw new RuntimeException("Customer email already exists!");
            }else {
                foundCustomer.setEmail(updatingCustomer.getEmail());
            }
        }
        foundCustomer.setFullName(updatingCustomer.getFullName());
        foundCustomer.setAddress(updatingCustomer.getAddress());
        foundCustomer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.customerRepository.save(foundCustomer);
    }

    public void deleteCustomer(Long id) {
        if(!this.customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invalid customer id!");
        }

        this.customerRepository.deleteById(id);
    }

}
