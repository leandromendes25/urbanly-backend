package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.response.PurchaseItemResponseDTO;
import com.leandromendes25.urbanly.entity.PurchaseItem;

import java.util.List;

public class PurchaseItemMapper {


        public static PurchaseItemResponseDTO toResponse(
                PurchaseItem item
        ){

            return new PurchaseItemResponseDTO(

                    item.getId(),

                    item.getProduct().getId(),

                    item.getProduct().getName(),

                    item.getProduct().getImageUrl(),

                    item.getQuantity(),

                    item.getPriceAtPurchase(),

                    item.getTotalPrice()
            );
        }

        public static List<PurchaseItemResponseDTO>
        toListResponse(List<PurchaseItem> items){

            return items.stream()
                    .map(PurchaseItemMapper::toResponse)
                    .toList();
    }}
