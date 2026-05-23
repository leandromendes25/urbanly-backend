package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.ReviewRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ReviewResponseDTO;
import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.entity.Review;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.mapper.ReviewMapper;
import com.leandromendes25.urbanly.repository.ClientRepository;
import com.leandromendes25.urbanly.repository.ProductRepository;
import com.leandromendes25.urbanly.repository.ReviewRepository;
import com.leandromendes25.urbanly.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public ReviewResponseDTO generateReview(ReviewRequestDTO dto, UUID productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
            String email = SecurityUtils.getEmailFromContext();
            Client client = clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            Review review = ReviewMapper.toEntity(dto,product,client);
            return ReviewMapper.toReviewResponseDTO(reviewRepository.save(review));
        }
        public List<ReviewResponseDTO> listAllReviews(){
        return ReviewMapper.listOfReviewsOfProduct(reviewRepository.findAll());
    }
        public void deleteReview(Long reviewId) throws UnauthorizedException {
            String email = SecurityUtils.getEmailFromContext();
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review não encontrada"));
            if (!review.getClient().getEmail().equals(email)){
                throw new UnauthorizedException("Usuário não tem permissão para deletar review");
            }
            reviewRepository.deleteById(reviewId);
        }
}
