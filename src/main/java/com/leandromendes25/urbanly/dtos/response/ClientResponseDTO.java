package com.leandromendes25.urbanly.dtos.response;

import java.time.LocalDateTime;

public record ClientResponseDTO(Long id, String name, String email, LocalDateTime createdAt) {
}
