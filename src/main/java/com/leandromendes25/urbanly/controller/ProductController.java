package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) throws UnauthorizedException {
    return ResponseEntity.ok().body(productService.generateProduct(dto));
}
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listOfProducts(){
        return ResponseEntity.ok().body(productService.listOfProducts());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO dto, @PathVariable UUID id){
        return ResponseEntity.ok().body(productService.updateProduct(dto, id));
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) throws UnauthorizedException {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
}
}
