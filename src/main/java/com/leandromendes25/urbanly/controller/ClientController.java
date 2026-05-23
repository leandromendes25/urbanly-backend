package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.Login;
import com.leandromendes25.urbanly.dtos.request.ClientRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ClientResponseDTO;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<ClientResponseDTO> saveClient(@RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.createClient(clientRequestDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) throws UnauthorizedException {
        return ResponseEntity.ok(clientService.autenticateClient(login));
    }
    @GetMapping
    public ResponseEntity<ClientResponseDTO> findClientByEmail(@RequestParam("email") String email){
    return ResponseEntity.ok(clientService.searchClientByEmail(email));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteClient(@PathVariable String email){
        clientService.deleteClient(email);
        return ResponseEntity.noContent().build();
    }

}
