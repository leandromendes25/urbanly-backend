package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Cart;
import com.leandromendes25.urbanly.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClient(Client client);
}
