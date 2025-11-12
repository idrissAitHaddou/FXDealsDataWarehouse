package com.example.FXDealsDataWarehouse.repositories;
import com.example.FXDealsDataWarehouse.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Optional<Deal> findByDealUniqueId(String dealUniqueId);
}

