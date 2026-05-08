package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.request.ClientRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ClientResponseDTO;
import com.leandromendes25.urbanly.entity.Client;

public class ClientMapper {
    public static Client toEntity(ClientRequestDTO dto){
        return Client.builder().name(dto.name()).email(dto.email()).password(dto.password()).build();
    }
    public static ClientResponseDTO toClientResponse(Client client){
        return new ClientResponseDTO(client.getId(), client.getName(), client.getEmail(), client.getCreatedAt());
    }
}
