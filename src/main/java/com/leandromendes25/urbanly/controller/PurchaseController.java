package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.PurchaseRequestDTO;
import com.leandromendes25.urbanly.dtos.response.PurchaseResponseDTO;
import com.leandromendes25.urbanly.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> checkout(){
        return ResponseEntity.ok(purchaseService.checkout());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseResponseDTO> getPurchaseById(@PathVariable Long purchaseId) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(purchaseId));
    }
}
