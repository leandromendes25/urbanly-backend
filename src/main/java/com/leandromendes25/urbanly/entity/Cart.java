package com.leandromendes25.urbanly.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public void addItem(CartItem item){
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item){
        items.remove(item);
        //vai estar removendo o item do carrinho
        item.setCart(null);
    }
    public BigDecimal getTotalPrice(){
        return items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Integer getTotalItems(){
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }
}
