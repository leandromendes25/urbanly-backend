package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
