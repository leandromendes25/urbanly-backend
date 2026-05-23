package com.leandromendes25.urbanly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Nome não pode ser nulo")
    private String name;
    @NotNull(message = "Descrição não pode ser nulo")
    private String description;
    @NotNull(message = "Preço não pode ser nulo")
    private BigDecimal price;
    @NotNull(message = "Imagem não pode ser nulo")
    private String imageUrl;
    @NotNull(message = "Quantidade não pode ser nulo")
    private Integer stock;
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;
    @CreationTimestamp
    private LocalDateTime createdAt;
}