package com.leandromendes25.urbanly.dtos.response;

import java.util.UUID;

public record SellerResponseDTO(UUID id, String storeName, String email) {
}
