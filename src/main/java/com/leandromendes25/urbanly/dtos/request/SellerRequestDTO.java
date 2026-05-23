package com.leandromendes25.urbanly.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SellerRequestDTO(String storeName, String email, @NotNull @Min(6) String password) {
}
