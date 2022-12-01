package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.ConsumptionRepository;
import com.api.beerdispenser.Exceptions.InternalError;
import com.api.beerdispenser.Exceptions.NotFound;

@Service
public class ConsumptionServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ConsumptionServiceImpl.class);
    @Autowired
    private ConsumptionRepository consumptionRepository;
    @Autowired
    DispensersServiceImpl dispensersServiceImpl;


    public List<Consumption> listAllUsages() {

        try {
            return consumptionRepository.findAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new InternalError(e.getMessage());
        }
    }

    public Consumption createConsumption() {
        try {
            Consumption newUsage = new Consumption(new Date(System.currentTimeMillis()));
            return consumptionRepository.save(newUsage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("Fail to create usage");
        }
    }

    public Consumption updateConsumption(Integer _id) {
        Optional<Consumption> consumption;
        try {
            consumption = consumptionRepository.findById(_id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError(e.getMessage());
        }
        if(consumption.isEmpty()){
            throw new NotFound("Usage not found");
        }
        try {
           consumption.get().setClose_at(new Date(System.currentTimeMillis()));
           return consumptionRepository.save(consumption.get());
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }

    }
    
    public Consumption addDispenserToConsumption(Dispenser dispenser){
        try{
            Consumption consumption= createConsumption();
            consumption.setDispenser(dispenser);
            return consumptionRepository.save(consumption);
        }catch (Exception e){
            throw new InternalError(e.getMessage());
        }
    }
}
