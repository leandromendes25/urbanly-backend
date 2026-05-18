package com.leandromendes25.urbanly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Review {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private String reviewerName;
    @Min(1)
    @Max(5)
    @NotNull(message = "A avaliação não pode ser nulo")
    private Integer ratings;
    @NotNull(message = "A mensagem é obrigátoria")
    private String comments;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}