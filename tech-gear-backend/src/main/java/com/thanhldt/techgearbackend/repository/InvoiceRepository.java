package com.thanhldt.techgearbackend.repository;

import com.thanhldt.techgearbackend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT invoice FROM Invoice invoice " +
            "INNER JOIN invoice.customer customer " +
            "WHERE customer.id = :customer_id")
    public List<Invoice> findAllByCustomerId(@Param("customer_id") Long customerId);

    @Query("SELECT invoice FROM Invoice invoice " +
            "INNER JOIN invoice.customer customer " +
            "WHERE invoice.id = :id AND customer.id = :customer_id")
    public Optional<Invoice> findByIdAndCustomerId(@Param("id") Long id, @Param("customer_id") Long customerId);

}
