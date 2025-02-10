package com.thanhldt.techgearbackend.service;

import com.thanhldt.techgearbackend.exception.ResourceNotFoundException;
import com.thanhldt.techgearbackend.model.Category;
import com.thanhldt.techgearbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid category id!"));
    }

    public void addCategory(Category newCategory) {
        newCategory.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newCategory.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.categoryRepository.save(newCategory);
    }

    public void updateCategory(Long id, Category updatingCategory) {
        Category foundCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid category id!"));
        foundCategory.setName(updatingCategory.getName());
        foundCategory.setDescription(updatingCategory.getDescription());
        foundCategory.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.categoryRepository.save(foundCategory);
    }

    public void deleteCategory(Long id) {
        if(!this.categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invalid category id!");
        }

        this.categoryRepository.deleteById(id);
    }

}
