package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<PurchaseItem, Long> {
}
