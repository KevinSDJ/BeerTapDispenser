package com.api.beerdispenser.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entity.Dispenser;


@Repository
public interface BeerDispenserRepository extends JpaRepository<Dispenser,UUID> {
    
    @Query(value="SELECT EXISTS(SELECT d._id,d.status FROM Dispenser as d WHERE d._id =:id AND d.status='OPEN');",nativeQuery = true)
    Boolean isOpen(@Param("id") UUID id);
}
