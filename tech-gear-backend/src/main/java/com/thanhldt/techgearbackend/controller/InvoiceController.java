package com.thanhldt.techgearbackend.controller;

import com.thanhldt.techgearbackend.model.Invoice;
import com.thanhldt.techgearbackend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(this.invoiceService.getAllInvoices());
    }

    @GetMapping("/customer-id/{customerId}")
    public ResponseEntity<List<Invoice>> getAllInvoicesByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.invoiceService.getAllInvoicesByCustomerId(customerId));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(this.invoiceService.getInvoiceById(id));
    }

    @GetMapping("/id/{id}/customer-id/{customerId}")
    public ResponseEntity<Invoice> getInvoiceByIdAndCustomerId(@PathVariable Long id, @PathVariable Long customerId) {
        return ResponseEntity.ok(this.invoiceService.getInvoiceByIdAndCustomerId(id, customerId));
    }

    @PostMapping("/")
    public ResponseEntity<String> addInvoice(@RequestBody Invoice newInvoice) {
        this.invoiceService.addInvoice(newInvoice);
        return ResponseEntity.ok("Add success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice updatingInvoice) {
        this.invoiceService.updateInvoice(id, updatingInvoice);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        this.invoiceService.deleteInvoice(id);
        return ResponseEntity.ok("Delete success!");
    }

}
