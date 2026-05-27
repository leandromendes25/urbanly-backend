package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.response.CartResponseDTO;
import com.leandromendes25.urbanly.entity.Cart;

public class CartMapper {
    public static CartResponseDTO toResponse(
            Cart cart
    ){

        return new CartResponseDTO(
                cart.getId(),
                CartItemMapper.listOfCartItemResponse(
                        cart.getItems()
                ),
                cart.getTotalPrice(),
                cart.getTotalItems()
        );
    }
}
