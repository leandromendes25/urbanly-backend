package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto){
        return Product.builder().name(dto.name()).imageUrl(dto.imageUrl())
                .description(dto.description()).price(dto.price()).stock(dto.stock())
                .build();
    }
    public static ProductResponseDTO toProductResponseDTO(Product product){
        return new ProductResponseDTO(product.getId(),product.getName(), product.getDescription(),
                product.getPrice(),product.getImageUrl(),product.getStock(), product.getCreatedAt());
    }
}
