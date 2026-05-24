package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.response.CartResponseDTO;
import com.leandromendes25.urbanly.entity.Cart;
import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.repository.CartRepository;
import com.leandromendes25.urbanly.repository.ClientRepository;
import com.leandromendes25.urbanly.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;

//    public CartResponseDTO getCart(){
//        String email = SecurityUtils.getEmailFromContext();
//        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
//        Cart cart = cartRepository.findByClient(client).orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
//
//    }
}
