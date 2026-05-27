package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.response.CartItemResponseDTO;
import com.leandromendes25.urbanly.dtos.response.ProductResponseDTO;
import com.leandromendes25.urbanly.entity.CartItem;

import java.util.List;

public class CartItemMapper {
    private static CartItemResponseDTO toResponse(
            CartItem cartItem
    ){
       ProductResponseDTO product = ProductMapper.toProductResponseDTO(
                cartItem.getProduct()
        );
        return new CartItemResponseDTO(

                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getImageUrl(),
                cartItem.getPriceAtMoment(),
                cartItem.getQuantity(),
                cartItem.getTotalPrice()

        );

}
    public static List<CartItemResponseDTO> listOfCartItemResponse(List<CartItem> listOfCartItem){
    return listOfCartItem.stream().map(CartItemMapper::toResponse).toList();
    }
}
