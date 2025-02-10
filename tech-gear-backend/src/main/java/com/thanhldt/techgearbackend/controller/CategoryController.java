package com.thanhldt.techgearbackend.controller;

import com.thanhldt.techgearbackend.model.Category;
import com.thanhldt.techgearbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> addCategory(@RequestBody Category newCategory) {
        this.categoryService.addCategory(newCategory);
        return ResponseEntity.ok("Add success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updatingCategory) {
        this.categoryService.updateCategory(id, updatingCategory);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete success!");
    }

}
