package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
