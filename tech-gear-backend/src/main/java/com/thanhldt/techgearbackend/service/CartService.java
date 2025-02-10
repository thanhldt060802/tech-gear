package com.thanhldt.techgearbackend.service;

import com.thanhldt.techgearbackend.exception.ResourceNotFoundException;
import com.thanhldt.techgearbackend.model.*;
import com.thanhldt.techgearbackend.repository.CartRepository;
import com.thanhldt.techgearbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Cart> getAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return this.cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart id!"));
    }

    public void addProductToCart(Long id, CartDetail newCartDetail) {
        Cart foundCart = this.cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart id!"));

        Product foundProduct = this.productRepository.findById(newCartDetail.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product id!"));

        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cartDetail) -> cartDetail.getProduct().getId().equals(newCartDetail.getProduct().getId()))
                .findFirst()
                .orElse(null);
        if(foundCartDetail != null) {
            foundCartDetail.setStock(foundCartDetail.getStock() + newCartDetail.getStock());
            if(foundCartDetail.getStock() > foundProduct.getStock()) {
                throw new RuntimeException("Invalid stock of product: " + foundProduct.getName() + ", available stock: " + foundProduct.getStock());
            }
        }else {
            newCartDetail.setCart(foundCart);
            newCartDetail.setProduct(foundProduct);
            if(newCartDetail.getStock() > foundProduct.getStock()) {
                throw new RuntimeException("Invalid stock of product: " + foundProduct.getName() + ", available stock: " + foundProduct.getStock());
            }
            foundCart.getCartDetails().add(newCartDetail);
        }

        foundCart.setTotalPrice(foundCart.getCartDetails().stream()
                .map((cardDetail) -> cardDetail.getProduct().getPrice()
                        .subtract(cardDetail.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(cardDetail.getProduct().getDiscountPercentage() / 100.0)))
                        .multiply(BigDecimal.valueOf(cardDetail.getStock())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        foundCart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.cartRepository.save(foundCart);
    }

    public void updateProductInCart(Long id, CartDetail updatingCartDetail) {
        Cart foundCart = this.cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart id!"));

        Product foundProduct = this.productRepository.findById(updatingCartDetail.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product id!"));

        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cardDetail) -> cardDetail.getProduct().getId().equals(updatingCartDetail.getProduct().getId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart detail id!"));
        foundCartDetail.setStock(updatingCartDetail.getStock());
        if(updatingCartDetail.getStock() > foundProduct.getStock()) {
            throw new RuntimeException("Invalid stock of product: " + foundProduct.getName() + ", available stock: " + foundProduct.getStock());
        }

        foundCart.setTotalPrice(foundCart.getCartDetails().stream()
                .map((cardDetail) -> cardDetail.getProduct().getPrice()
                        .subtract(cardDetail.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(cardDetail.getProduct().getDiscountPercentage() / 100.0)))
                        .multiply(BigDecimal.valueOf(cardDetail.getStock())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        foundCart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.cartRepository.save(foundCart);
    }

    public void deleteProductFromCart(Long id, Long productId) {
        Cart foundCart = this.cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cart id!"));

        if(!this.productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Invalid product id!");
        }

        CartDetail foundCartDetail = foundCart.getCartDetails().stream()
                .filter((cardDetail) -> cardDetail.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product id!"));
        foundCart.getCartDetails().remove(foundCartDetail);

        foundCart.setTotalPrice(foundCart.getCartDetails().stream()
                .map((cardDetail) -> cardDetail.getProduct().getPrice()
                        .subtract(cardDetail.getProduct().getPrice()
                                .multiply(BigDecimal.valueOf(cardDetail.getProduct().getDiscountPercentage() / 100.0)))
                        .multiply(BigDecimal.valueOf(cardDetail.getStock())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        foundCart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.cartRepository.save(foundCart);
    }

}
