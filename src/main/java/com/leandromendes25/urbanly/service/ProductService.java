package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.ProductRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.dtos.response.SellerResponseDTO;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.entity.Seller;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.mapper.ProductMapper;
import com.leandromendes25.urbanly.mapper.SellerMapper;
import com.leandromendes25.urbanly.repository.ProductRepository;
import com.leandromendes25.urbanly.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO generateProduct(ProductRequestDTO dto) throws UnauthorizedException {
        if(dto == null){
        throw new RuntimeException("Produto não pode ser vázio");
    }
        Seller seller = SecurityUtils.getAuthenticatedSeller();
        Product productEntity = ProductMapper.toEntity(dto);
        productEntity.setSeller(seller);
        Product product = productRepository.save(productEntity);
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
    public void removeProduct(UUID id) throws UnauthorizedException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        String email = SecurityUtils.getEmailFromContext();
        if (!product.getSeller().getEmail().equals(email)){
            throw new UnauthorizedException("o email logado não é o mesmo do que cadastrou o produto");
        }
        productRepository.deleteById(id);
    }

}
