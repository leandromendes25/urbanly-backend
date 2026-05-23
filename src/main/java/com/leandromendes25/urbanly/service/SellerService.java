package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.ClientRequestDTO;
import com.leandromendes25.urbanly.dtos.request.Login;
import com.leandromendes25.urbanly.dtos.request.SellerRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ClientResponseDTO;
import com.leandromendes25.urbanly.dtos.response.SellerResponseDTO;
import com.leandromendes25.urbanly.entity.Seller;
import com.leandromendes25.urbanly.exceptions.ConflictException;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.mapper.ClientMapper;
import com.leandromendes25.urbanly.mapper.SellerMapper;
import com.leandromendes25.urbanly.repository.SellerRepository;
import com.leandromendes25.urbanly.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public SellerResponseDTO generateSeller(SellerRequestDTO sellerRequestDTO){
    if (sellerRepository.findByEmail(sellerRequestDTO.email()).isPresent()){
        throw new ConflictException("Seller já cadastrado");
    }
    String encryptedPassowrd = passwordEncoder.encode(sellerRequestDTO.password());
    SellerRequestDTO encryptedDto = new SellerRequestDTO(sellerRequestDTO.storeName(), sellerRequestDTO.email(),encryptedPassowrd);
    Seller seller = SellerMapper.toEntity(encryptedDto);
    return SellerMapper.toSellerResponse(sellerRepository.save(seller));
    }
    public String autenticateSeller(Login login) throws UnauthorizedException {
        try{
            Authentication authentication = authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(login.email(), login.password()));
            return "Bearer " + jwtUtil.generateToken(authentication.getName());
        }catch (BadCredentialsException | UsernameNotFoundException ex){
            throw new UnauthorizedException("Usuario ou senha inválidos");
        }
    }
    public SellerResponseDTO searchSellerByEmail(String email){
        return SellerMapper.toSellerResponse(sellerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado: " + email)));
    }
    public void deleteSeller(String email){ sellerRepository.deleteByEmail(email);
    }
}
