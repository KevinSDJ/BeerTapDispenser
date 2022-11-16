package com.api.beerdispenser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.beerdispenser.entities.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary,Long> {
    
}
