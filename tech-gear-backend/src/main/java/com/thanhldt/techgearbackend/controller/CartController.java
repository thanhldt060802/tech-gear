package com.thanhldt.techgearbackend.controller;

import com.thanhldt.techgearbackend.model.Cart;
import com.thanhldt.techgearbackend.model.CartDetail;
import com.thanhldt.techgearbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(this.cartService.getAllCarts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cartService.getCartById(id));
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long id, @RequestBody CartDetail newCartDetail) {
        this.cartService.addProductToCart(id, newCartDetail);
        return ResponseEntity.ok("Add product to cart success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateProductInCart(@PathVariable Long id, @RequestBody CartDetail updatingCartDetail) {
        this.cartService.updateProductInCart(id, updatingCartDetail);
        return ResponseEntity.ok("Update product in cart success!");
    }

    @DeleteMapping("/id/{id}/product-id/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        this.cartService.deleteProductFromCart(id, productId);
        return ResponseEntity.ok("Delete product from cart success!");
    }

}
