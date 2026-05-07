package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
