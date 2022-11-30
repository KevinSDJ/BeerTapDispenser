package com.api.beerdispenser.services;

import java.util.UUID;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.projections.ConsumptionFullData;
import java.util.List;

public interface IConsumptionService {
    Consumption createConsumption(UUID forgkey);
    Consumption updateConsumption(UUID forgkey);
    Consumption intermediateOp(UUID forgkey,String status);
    List<ConsumptionFullData> listAllUsages();
}
