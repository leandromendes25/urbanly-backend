package com.leandromendes25.urbanly.repository;

import com.leandromendes25.urbanly.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
}
