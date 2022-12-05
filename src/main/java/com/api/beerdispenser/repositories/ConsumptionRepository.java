package com.api.beerdispenser.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Consumption;


@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long> {

    @Query(value="SELECT TOP 1 * FROM  Consumption AS c WHERE (c.close_at IS NULL) AND c.dispenser_id=:id", nativeQuery = true)
    Consumption findOneWhereOpenAndByDispenser(@Param("id") UUID id) ;
}