package com.leandromendes25.urbanly.mapper;

import com.leandromendes25.urbanly.dtos.response.PurchaseResponseDTO;
import com.leandromendes25.urbanly.entity.Purchase;

public class PurchaseMapper {

    public static PurchaseResponseDTO toResponse(
            Purchase purchase
    ){

        return new PurchaseResponseDTO(

                purchase.getId(),

                purchase.getStatus(),

                purchase.getTotalAmount(),


                PurchaseItemMapper.toListResponse(
                        purchase.getItems()
                )
                ,purchase.getCreatedAt()

        );
    }
}
