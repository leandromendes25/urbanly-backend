package com.leandromendes25.urbanly.dtos.request;

import jakarta.validation.constraints.Min;

public record UpdateCartItemRequestDTO(@Min(1) Integer quantity) {
}
