package com.api.beerdispenser.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.projections.ConsumptionFull;
import com.api.beerdispenser.projections.ConsumptionFullData;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Integer> {

    @Query(value ="SELECT c._id,c.open_at,c.close_at,c.usage_amount FROM Consumption as c",nativeQuery = true)
    List<ConsumptionFull> findAllFull();

    @Query(value ="SELECT c._id,c.open_at,c.close_at,c.usage_amount,c.dispenser_id FROM Consumption as c where c.dispenser_id=:id",nativeQuery = true)
    List<ConsumptionFullData> findByDispenserId(@Param("id") UUID id);

    @Query(value="SELECT TOP 1 * FROM  Consumption AS c WHERE (c.close_at IS NULL) AND c.dispenser_id=:id", nativeQuery = true)
    Consumption findOneWhereOpenAndByDispenser(@Param("id") UUID id) ;
}