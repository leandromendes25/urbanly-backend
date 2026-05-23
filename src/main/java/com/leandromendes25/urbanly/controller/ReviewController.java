package com.leandromendes25.urbanly.controller;

import com.leandromendes25.urbanly.dtos.request.ReviewRequestDTO;
import com.leandromendes25.urbanly.dtos.response.ReviewResponseDTO;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product/{productId}/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO dto, @PathVariable UUID productId){
    return ResponseEntity.ok().body(reviewService.generateReview(dto,  productId));
    }
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findAllReviews(){
        return ResponseEntity.ok().body(reviewService.listAllReviews());
    }
    @DeleteMapping("{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) throws UnauthorizedException {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
