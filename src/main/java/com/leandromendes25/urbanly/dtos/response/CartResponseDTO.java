package com.leandromendes25.urbanly.dtos.response;

import java.util.List;

public record CartResponseDTO(
Long id, List<CartItemResponseDTO> items
) {
}
