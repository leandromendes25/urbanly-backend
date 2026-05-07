package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
