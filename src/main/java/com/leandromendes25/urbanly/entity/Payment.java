package com.leandromendes25.urbanly.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String stripePaymentIntentId;
    private String status;
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
}
