package com.api.beerdispenser.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entity.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary,Long> {
    
    @Query(value="SELECT EXISTS(SELECT TOP 1 FROM Summary AS s WHERE s.dispenser_id=:id );",nativeQuery=true)
    Boolean existSummary(@Param("id") UUID id);

    @Query(value = "SELECT TOP 1 * FROM Summary AS s WHERE s.dispenser_id=:id" ,nativeQuery = true)
    Summary findByDispenserId(@Param("id") UUID id);
}
