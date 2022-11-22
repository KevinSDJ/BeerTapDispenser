package com.api.beerdispenser.services;

import java.util.UUID;
import com.api.beerdispenser.entities.Consumption;

public interface IConsumptionService {
    Consumption createConsumption(UUID forgkey);
    Consumption updateConsumption(UUID forgkey);
    Consumption intermediateOp(UUID forgkey,String status);
}
