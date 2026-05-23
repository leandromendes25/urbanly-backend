package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.Login;
import com.leandromendes25.urbanly.dtos.request.SellerRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ClientResponseDTO;
import com.leandromendes25.urbanly.dtos.response.SellerResponseDTO;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<SellerResponseDTO> saveSeller(@Valid @RequestBody SellerRequestDTO dto){
    return ResponseEntity.ok().body(sellerService.generateSeller(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) throws UnauthorizedException {
        return ResponseEntity.ok(sellerService.autenticateSeller(login));
    }
    @GetMapping
    public ResponseEntity<SellerResponseDTO> findClientByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(sellerService.searchSellerByEmail(email));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteClient(@PathVariable String email){
        sellerService.deleteSeller(email);
        return ResponseEntity.noContent().build();
    }
}
