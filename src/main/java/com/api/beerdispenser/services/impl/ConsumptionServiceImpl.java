package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Status;
import com.api.beerdispenser.projections.ConsumptionFullData;
import com.api.beerdispenser.repositories.ConsumptionRepository;
import com.api.beerdispenser.services.IConsumptionService;
import com.api.beerdispenser.Exceptions.BadRequest;
import com.api.beerdispenser.Exceptions.InternalError;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {

    private final Logger log = LoggerFactory.getLogger(ConsumptionServiceImpl.class);
    @Autowired
    private ConsumptionRepository consumptionRepository;
    @Autowired
    DispensersServiceImpl dispensersServiceImpl;

    @Override
    public Consumption createConsumption(UUID forgkey) {
        try {
            Dispenser dispenser = dispensersServiceImpl.updateState(forgkey, Status.valueOf("OPEN").toString());
            Consumption newUsage = new Consumption(new Date(System.currentTimeMillis()));
            newUsage.setDispenser(dispenser);
            return consumptionRepository.save(newUsage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("Fail to create usage");
        }
    }

    @Override
    public Consumption updateConsumption(UUID forgkey) {
        if (!dispensersServiceImpl.isOpen(forgkey)) {
            throw new BadRequest("this dispenser is already closed");
        } else {
            try {
                Dispenser dispenser = dispensersServiceImpl.updateState(forgkey,Status.valueOf("CLOSED").toString());
                return null;
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new InternalError("Fail updateConsumption process");
            }

        }
    }

    @Override
    public Consumption intermediateOp(UUID forgkey, String status) {
        try{
            Status.valueOf(status);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
        if (status.toUpperCase().equals("OPEN")){
            if (!dispensersServiceImpl.isOpen(forgkey)) {
                return createConsumption(forgkey);
            } else {
                throw new BadRequest("this dispenser is already open");
            }
        }
        return updateConsumption(forgkey);
    }

    @Override
    public List<ConsumptionFullData> listAllUsages() {
         
        try{
            return consumptionRepository.findAllWhereId();
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
    }

    
}
