package com.thanhldt.techgearbackend.repository;

import com.thanhldt.techgearbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT COUNT(customer) > 0 FROM Customer customer " +
            "WHERE LOWER(customer.username) = LOWER(:username)")
    public boolean existsByUsername(@Param("username") String username);

    @Query("SELECT COUNT(customer) > 0 FROM Customer customer " +
            "WHERE LOWER(customer.email) = LOWER(:email)")
    public boolean existsByEmail(@Param("email") String email);

    @Query("SELECT customer FROM Customer customer " +
            "WHERE LOWER(customer.username) = LOWER(:username)")
    public Optional<Customer> findCustomerByUsername(@Param("username") String username);

}
