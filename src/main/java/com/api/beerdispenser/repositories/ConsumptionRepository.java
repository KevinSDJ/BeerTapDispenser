package com.api.beerdispenser.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Usage;



@Repository
public interface ConsumptionRepository extends JpaRepository<Usage,Long> {

    @Query(value="SELECT TOP 1 * FROM  Consumption AS c WHERE (c.close_at IS NULL) AND c.dispenser_id=:id", nativeQuery = true)
    Usage findOneWhereOpenAndByDispenser(@Param("id") UUID id);

    @Query(value="SELECT * FROM Consumption AS c WHERE c.dispenser_id=:id ;",nativeQuery = true)
    List<Usage> findByDispenserId(@Param("id") UUID id);
}