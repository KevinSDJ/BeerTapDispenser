package com.api.beerdispenser.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entity.Dispenser;


@Repository
public interface BeerDispenserRepository extends JpaRepository<Dispenser,UUID> {
    
    @Query(value="SELECT EXISTS(SELECT d._id FROM Dispenser as d WHERE d._id =:id);",nativeQuery = true)
    Boolean existDispenser(@Param("id") UUID id);
    
}
