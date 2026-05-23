package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.request.SellerRequestDTO;
import com.leandromendes25.urbanly.dtos.response.SellerResponseDTO;
import com.leandromendes25.urbanly.entity.Seller;

public class SellerMapper {
    public static Seller toEntity(SellerRequestDTO dto){
        return Seller.builder().email(dto.email()).password(dto.password()).storeName(dto.storeName()).build();
    }
    public static SellerResponseDTO toSellerResponse(Seller seller){
        return new SellerResponseDTO(seller.getId(), seller.getStoreName(), seller.getEmail());
    }
}
