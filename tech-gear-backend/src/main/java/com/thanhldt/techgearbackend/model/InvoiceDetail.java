package com.thanhldt.techgearbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer stock;
    private BigDecimal price;
    private Integer discountPercentage;

    public Long getId() {
        return this.id;
    }

    public Invoice getInvoice() {
        return this.invoice;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getStock() {
        return this.stock;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
