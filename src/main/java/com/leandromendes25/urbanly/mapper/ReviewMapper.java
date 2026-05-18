package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.request.ReviewRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ReviewResponseDTO;
import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.entity.Review;

import java.util.List;

public class ReviewMapper {
    public static Review toEntity(ReviewRequestDTO dto, Product product, Client client){
        return Review.builder().product(product).client(client).ratings(dto.ratings()).comments(dto.comments()).build();
    }
    public static ReviewResponseDTO toReviewResponseDTO(Review review) {

        return new ReviewResponseDTO(
                review.getId(),
                review.getClient().getName(),
                review.getRatings(),
                review.getComments(),
                review.getProduct().getId(),
                review.getProduct().getName()
        );
    }
    public static List<ReviewResponseDTO> listOfReviewsOfProduct(List<Review> listOfReview){
        return listOfReview.stream().map(ReviewMapper::toReviewResponseDTO).toList();
    }
}
