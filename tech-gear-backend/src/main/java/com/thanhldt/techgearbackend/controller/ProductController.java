package com.thanhldt.techgearbackend.controller;

import com.thanhldt.techgearbackend.model.Product;
import com.thanhldt.techgearbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/category-id/{categoryId}")
    public ResponseEntity<List<Product>> getAllProductByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(this.productService.getAllProductsByCategoryId(categoryId));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) {
        this.productService.addProduct(newProduct);
        return ResponseEntity.ok("Add success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatingProduct) {
        this.productService.updateProduct(id, updatingProduct);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok("Delete success!");
    }

}
