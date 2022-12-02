package com.api.beerdispenser.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HelpRedirByCase {
    
    @Autowired 
    private ConsumptionServiceImpl consumptionServiceImpl;


    public Consumption redirByCase(Dispenser dispenser){
        if(dispenser.getStatus().equals("OPEN")){
            return consumptionServiceImpl.createConsumption(dispenser);
        }
        return consumptionServiceImpl.updateConsumption(dispenser);
    }

}
