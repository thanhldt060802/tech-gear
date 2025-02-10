package com.thanhldt.techgearbackend.service;

import com.thanhldt.techgearbackend.exception.ResourceNotFoundException;
import com.thanhldt.techgearbackend.model.*;
import com.thanhldt.techgearbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<Invoice> getAllInvoices() {
        return this.invoiceRepository.findAll();
    }

    public List<Invoice> getAllInvoicesByCustomerId(Long customerId) {
        return this.invoiceRepository.findAllByCustomerId(customerId);
    }

    public Invoice getInvoiceById(Long id) {
        return this.invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid invoice id!"));
    }

    public Invoice getInvoiceByIdAndCustomerId(Long id, Long customerId) {
        return this.invoiceRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid invoice id or customer id!"));
    }

    @Transactional
    public void addInvoice(Invoice newInvoice) {
        newInvoice.setInvoiceDetails(new ArrayList<>());

        Customer foundCustomer = this.customerRepository.findById(newInvoice.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id!"));
        newInvoice.setCustomer(foundCustomer);

        Cart foundCart = this.cartRepository.findById(newInvoice.getCustomer().getCart().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart id!"));
        foundCart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        for(CartDetail cartDetail : foundCart.getCartDetails()) {
            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setInvoice(newInvoice);
            newInvoiceDetail.setProduct(cartDetail.getProduct());
            newInvoiceDetail.setStock(cartDetail.getStock());
            newInvoiceDetail.setPrice(cartDetail.getProduct().getPrice());
            newInvoiceDetail.setDiscountPercentage(cartDetail.getProduct().getDiscountPercentage());
            newInvoice.getInvoiceDetails().add(newInvoiceDetail);

            if(newInvoiceDetail.getProduct().getStock() < newInvoiceDetail.getStock()) {
                throw new RuntimeException("Invalid stock of product: " + cartDetail.getProduct().getName());
            }
            newInvoiceDetail.getProduct().setStock(newInvoiceDetail.getProduct().getStock() - newInvoiceDetail.getStock());

            this.productRepository.save(newInvoiceDetail.getProduct());
        }

        newInvoice.setTotalPrice(newInvoice.getInvoiceDetails().stream()
                .map((invoiceDetail) -> invoiceDetail.getPrice()
                        .subtract(invoiceDetail.getPrice()
                                .multiply(BigDecimal.valueOf(invoiceDetail.getProduct().getDiscountPercentage() / 100.0)))
                        .multiply(BigDecimal.valueOf(invoiceDetail.getStock())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        newInvoice.setStatus("Start");
        newInvoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newInvoice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        foundCart.getCartDetails().clear();
        foundCart.setTotalPrice(BigDecimal.valueOf(0));

        this.invoiceRepository.save(newInvoice);
        this.cartRepository.save(foundCart);
    }

    public void updateInvoice(Long id, Invoice updatingInvoice) {
        Invoice foundInvoice = this.invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid invoice id!"));
        foundInvoice.setStatus(updatingInvoice.getStatus());
        foundInvoice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.invoiceRepository.save(foundInvoice);
    }

    public void deleteInvoice(Long id) {
        if(!this.invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invalid invoice id!");
        }
        
        this.invoiceRepository.deleteById(id);
    }

}
