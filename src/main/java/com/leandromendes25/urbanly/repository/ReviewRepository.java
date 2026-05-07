package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
