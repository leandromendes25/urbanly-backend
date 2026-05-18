package com.leandromendes25.urbanly.dtos.response;

import java.util.UUID;

public record ReviewResponseDTO(Long id, String reviewerName, Integer ratings, String comments ,  UUID productId,String productName) {
}
