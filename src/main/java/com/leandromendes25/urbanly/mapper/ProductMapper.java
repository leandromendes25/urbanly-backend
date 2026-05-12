package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.entity.Product;

import java.util.ArrayList;
import java.util.List;

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
    public static List<ProductResponseDTO> toListOfProductResponseDTO(List<Product> listOfProduct){
         return listOfProduct.stream().map(ProductMapper::toProductResponseDTO).toList();
    }
    public static void updateProductFromDTO(
            Product product,
            ProductRequestDTO dto
    ){
        if(dto.name() != null){
            product.setName(dto.name());
        }

        if(dto.description() != null){
            product.setDescription(dto.description());
        }

        if(dto.price() != null){
            product.setPrice(dto.price());
        }

        if(dto.imageUrl() != null){
            product.setImageUrl(dto.imageUrl());
        }

        if(dto.stock() != null){
            product.setStock(dto.stock());
        }
    }
}
