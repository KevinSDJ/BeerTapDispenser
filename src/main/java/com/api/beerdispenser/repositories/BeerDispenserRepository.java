package com.api.beerdispenser.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Dispenser;

@Repository
public interface BeerDispenserRepository extends JpaRepository<Dispenser,UUID> {
    

}
