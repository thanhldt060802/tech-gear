package com.thanhldt.techgearbackend.service;

import com.thanhldt.techgearbackend.exception.ResourceNotFoundException;
import com.thanhldt.techgearbackend.model.Category;
import com.thanhldt.techgearbackend.model.Product;
import com.thanhldt.techgearbackend.repository.CategoryRepository;
import com.thanhldt.techgearbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        if(!this.categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Invalid category id!");
        }

        return this.productRepository.findAllByCategoryId(categoryId);
    }

    public Product getProductById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product id!"));
    }

    public void addProduct(Product newProduct) {
        if(newProduct.getCategory() != null) {
            if(newProduct.getCategory().getId() != null) {
                Category foundCategory = this.categoryRepository.findById(newProduct.getCategory().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Invalid category id!"));
                newProduct.setCategory(foundCategory);
            }else {
                newProduct.setCategory(null);
            }
        }
        newProduct.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.productRepository.save(newProduct);
    }

    public void updateProduct(Long id, Product updatingProduct) {
        Product foundProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product id!"));
        if(updatingProduct.getCategory() != null) {
            if(updatingProduct.getCategory().getId() != null) {
                Category foundCategory = this.categoryRepository.findById(updatingProduct.getCategory().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Invalid category id!"));
                foundProduct.setCategory(foundCategory);
            }else {
                foundProduct.setCategory(null);
            }
        }
        foundProduct.setName(updatingProduct.getName());
        foundProduct.setDescription(updatingProduct.getDescription());
        foundProduct.setPrice(updatingProduct.getPrice());
        foundProduct.setDiscountPercentage(updatingProduct.getDiscountPercentage());
        foundProduct.setStock(updatingProduct.getStock());
        foundProduct.setAvailable(updatingProduct.getAvailable());
        foundProduct.setImageUrl(updatingProduct.getImageUrl());
        foundProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.productRepository.save(foundProduct);
    }

    public void deleteProduct(Long id) {
        if(!this.productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invalid product id!");
        }

        this.productRepository.deleteById(id);
    }

}
