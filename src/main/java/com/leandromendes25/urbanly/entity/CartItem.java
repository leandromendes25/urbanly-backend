package com.leandromendes25.urbanly.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
    public class CartItem {
     //CartItem serve para armazenar informações customizadas do produto, nesse caso para quantidade e preço no momento
        @Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE)
        private Long id;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cart_id")
        private Cart cart;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;
        private Integer quantity;
        private BigDecimal priceAtMoment;

        public BigDecimal getTotalPrice(){
            return priceAtMoment.multiply(BigDecimal.valueOf(quantity));
        }
}
