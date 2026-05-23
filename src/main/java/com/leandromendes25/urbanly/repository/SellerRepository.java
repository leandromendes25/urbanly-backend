package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {

    Optional<Seller> findByEmail(String email);
    void deleteByEmail(String email);
}
