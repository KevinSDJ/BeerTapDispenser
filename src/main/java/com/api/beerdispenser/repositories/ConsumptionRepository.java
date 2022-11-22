package com.api.beerdispenser.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.projections.ConsumptionFullData;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long> {

    @Query(value ="SELECT c.id,c.open_at,c.close_at FROM Consumptions as c WHERE c.dipenser_id=:forgkey",nativeQuery = true)
    List<ConsumptionFullData> findAllWhereId(@Param("forgkey") UUID forgkey);
}
