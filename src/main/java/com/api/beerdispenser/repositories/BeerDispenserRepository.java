package com.api.beerdispenser.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.projections.DispenserFit;
import com.api.beerdispenser.projections.DispenserFull;

@Repository
public interface BeerDispenserRepository extends JpaRepository<Dispenser,UUID> {
    @Query(value="SELECT s._id,s.flow_amount FROM Dispensers as s;", nativeQuery = true)
    List<DispenserFit> findAllFit();

    @Query(value="SELECT s._id,s.flow_amount,s.status FROM Dispensers as s;",nativeQuery = true)
    List<DispenserFull> findAllFull();
}
