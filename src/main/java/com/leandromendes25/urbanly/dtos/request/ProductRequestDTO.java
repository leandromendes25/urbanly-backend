package com.leandromendes25.urbanly.dtos.request;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, String imageUrl, Integer stock) {
}
