package com.leandromendes25.urbanly.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseItemResponseDTO(Long id, UUID productId, String productName, String imageUrl, Integer quantity,
                                      BigDecimal priceAtPurchase, BigDecimal totalPrice) {
}
