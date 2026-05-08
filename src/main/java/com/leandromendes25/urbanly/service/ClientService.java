package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.Login;
import com.leandromendes25.urbanly.dtos.request.ClientRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ClientResponseDTO;
import com.leandromendes25.urbanly.exceptions.ConflictException;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.exceptions.UnathorizedException;
import com.leandromendes25.urbanly.mapper.ClientMapper;
import com.leandromendes25.urbanly.repository.ClientRepository;
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
public class ClientService {

    private final ClientRepository clientRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO){
        if (clientRepository.findByEmail(clientRequestDTO.email().toLowerCase()).isPresent()) {
            throw new ConflictException("Email já cadastrado");
        }
        String encryptedPassowrd = passwordEncoder.encode(clientRequestDTO.password());
    ClientRequestDTO encryptedDto = new ClientRequestDTO(clientRequestDTO.name(), clientRequestDTO.email(),encryptedPassowrd);
    var user = clientRepository.save(ClientMapper.toEntity(encryptedDto ));
    return ClientMapper.toClientResponse(user);
}

    public String autenticateClient(Login login) throws UnathorizedException {
        try{
            Authentication authentication = authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(login.email(), login.password()));
            return "Bearer " + jwtUtil.generateToken(authentication.getName());
        }catch (BadCredentialsException | UsernameNotFoundException ex){
            throw new UnathorizedException("Usuario ou senha inválidos");
        }
    }
    public ClientResponseDTO searchClientByEmail(String email){
            return ClientMapper.toClientResponse(clientRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado: " + email)));
    }
    public void deleteClient(String email){
        clientRepository.deleteByEmail(email);
    }
}
