package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.CartItemRequestDTO;
import com.leandromendes25.urbanly.dtos.request.UpdateCartItemRequestDTO;
import com.leandromendes25.urbanly.dtos.response.CartResponseDTO;
import com.leandromendes25.urbanly.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }
    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addItemToCart(@Valid @RequestBody CartItemRequestDTO dto){
    return ResponseEntity.ok(cartService.addItem(dto));
    }
    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponseDTO> updateItemQuantity(@PathVariable Long itemId, @Valid @RequestBody UpdateCartItemRequestDTO dto){
    return ResponseEntity.ok(cartService.updateItemQuantity(itemId,dto));
    }
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponseDTO> removeItem(@PathVariable Long itemId){
        return ResponseEntity.ok(cartService.removeItem(itemId));
    }
}
