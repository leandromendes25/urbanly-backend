package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.mapper.ProductMapper;
import com.leandromendes25.urbanly.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO generateProduct(ProductRequestDTO dto){
    if(dto == null){
        throw new RuntimeException("Produto não pode ser vázio");
    }
    Product product = productRepository.save(ProductMapper.toEntity(dto));
    return ProductMapper.toProductResponseDTO(product);
    }
}
