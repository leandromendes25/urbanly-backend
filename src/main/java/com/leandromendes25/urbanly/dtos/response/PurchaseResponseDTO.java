package com.leandromendes25.urbanly.dtos.response;

import com.leandromendes25.urbanly.entity.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PurchaseResponseDTO(Long id, PurchaseStatus status, BigDecimal totalAmount, List<PurchaseItemResponseDTO> items, LocalDateTime createdAt) {
}
