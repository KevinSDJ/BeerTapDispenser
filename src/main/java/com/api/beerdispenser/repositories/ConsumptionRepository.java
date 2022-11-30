package com.api.beerdispenser.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.projections.ConsumptionFullData;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long> {

    @Query(value ="SELECT c._id,c.open_at,c.close_at,c.usage_amount FROM Consumption as c",nativeQuery = true)
    List<ConsumptionFullData> findAllWhereId();
}
