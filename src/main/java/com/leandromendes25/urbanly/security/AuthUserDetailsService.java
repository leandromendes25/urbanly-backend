package com.leandromendes25.urbanly.security;

import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Seller;
import com.leandromendes25.urbanly.repository.ClientRepository;
import com.leandromendes25.urbanly.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Optional<Client> client =
                clientRepository.findByEmail(email);

        if(client.isPresent()){
            return client.get();
        }

        Optional<Seller> seller =
                sellerRepository.findByEmail(email);

        if(seller.isPresent()){
            return seller.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}