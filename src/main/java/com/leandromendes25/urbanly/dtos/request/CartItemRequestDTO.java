package com.leandromendes25.urbanly.dtos.request;

import jakarta.validation.constraints.Min;

import java.util.UUID;

public record CartItemRequestDTO(UUID productId, @Min(1) Integer quantity) {
}
