package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Status;
import com.api.beerdispenser.repositories.ConsumptionRepository;
import com.api.beerdispenser.services.IConsumptionService;
import com.api.beerdispenser.Exceptions.BadRequest;
import com.api.beerdispenser.Exceptions.InternalError;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {


    private final Logger log= LoggerFactory.getLogger(ConsumptionServiceImpl.class);
    @Autowired
    ConsumptionRepository consumptionRepository;
    @Autowired
    DispensersServiceImpl dispensersServiceImpl;

    @Override
    public Consumption createConsumption(UUID forgkey) {
        Dispenser dispenser= dispensersServiceImpl.updateState(forgkey,"OPEN");
        Consumption newUsage=new Consumption(new Date(System.currentTimeMillis()));
        newUsage.setDispenser(dispenser);
        try{
            return consumptionRepository.save(newUsage);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new InternalError("Fail to create usage");
        }
    }

    @Override
    public Consumption updateConsumption(UUID forgkey) {
        Dispenser dispenser= dispensersServiceImpl.findOneDispenser(forgkey);
        return null;
    }

    @Override
    public Consumption intermediateOp(UUID forgkey, String status) {
        if(Status.valueOf(status).getStatus() == "OPEN"){
            if(!dispensersServiceImpl.isOpen(forgkey)){
                return createConsumption(forgkey);
            }else{
                throw new BadRequest("this dispenser is already open");
            }
        }
        return updateConsumption(forgkey);
    }
    
}
