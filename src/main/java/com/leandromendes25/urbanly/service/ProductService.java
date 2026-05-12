package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.mapper.ProductMapper;
import com.leandromendes25.urbanly.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<ProductResponseDTO> listOfProducts(){
        return ProductMapper.toListOfProductResponseDTO(productRepository.findAll());
    }
    public ProductResponseDTO updateProduct(ProductRequestDTO dto, UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        ProductMapper.updateProductFromDTO(product, dto);
        productRepository.save(product);
        return ProductMapper.toProductResponseDTO(product);
    }
    public void removeProduct(UUID id){
        productRepository.deleteById(id);
    }

}
