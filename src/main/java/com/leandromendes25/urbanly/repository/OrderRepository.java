package com.leandromendes25.urbanly.repository;
import com.leandromendes25.urbanly.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase, Long> {
}
