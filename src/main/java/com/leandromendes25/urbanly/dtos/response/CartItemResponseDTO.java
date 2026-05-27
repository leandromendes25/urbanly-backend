package com.leandromendes25.urbanly.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponseDTO(Long id, UUID productId, String productName, String imageUrl,
                                  BigDecimal priceAtMoment, Integer quantity, BigDecimal totalPrice) {
}
