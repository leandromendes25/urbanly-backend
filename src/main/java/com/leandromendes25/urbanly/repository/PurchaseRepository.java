package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByClientOrderByCreatedAtDesc(Client client);
}
