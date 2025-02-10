package com.thanhldt.techgearbackend.repository;

import com.thanhldt.techgearbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT product FROM Product product " +
            "INNER JOIN product.category category " +
            "WHERE category.id = :category_id")
    public List<Product> findAllByCategoryId(@Param("category_id") Long categoryId);

}
