package com.leandromendes25.urbanly.entity;

import com.leandromendes25.urbanly.entity.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Purchase {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    private BigDecimal totalAmount;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseItem> items;

    public BigDecimal getTotalAmount(){

        return items.stream()
                .map(PurchaseItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
