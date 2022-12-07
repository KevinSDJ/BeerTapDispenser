package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.beerdispenser.entities.Usage;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.ConsumptionRepository;
import com.api.beerdispenser.Exceptions.InternalError;
import com.api.beerdispenser.Exceptions.NotFound;


@Service

public class ConsumptionServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ConsumptionServiceImpl.class);
    @Autowired
    private ConsumptionRepository consumptionRepository;

    public List<Usage> listAllUsages() {

        try {
            return consumptionRepository.findAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new InternalError(e.getMessage());
        }
    }
    public List<Usage> listAllByDispenserId(UUID id){
        List<Usage> usages;
        try{
            usages = consumptionRepository.findByDispenserId(id);
        }catch(Exception e){
            throw new InternalError("Error api process"); 
        }
        if(usages.equals(null)){
            throw new NotFound("Dispensers not find");
        }
        return usages;
    }

    public Usage getInOpen(UUID id){
        Usage consumption;
        try{
            consumption = consumptionRepository.findOneWhereOpenAndByDispenser(id);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
        log.info(consumption.toString());
        if(consumption.equals(null)){
            throw new NotFound("Not find usage open");
        }
        return consumption;
    }
    
   
    public Usage createConsumption(Dispenser dispenser) {
        try {
            Usage newUsage = new Usage(new Date(System.currentTimeMillis()));
            newUsage.setDispenser(dispenser);
            newUsage.setFlow_volume(dispenser.getFlow_volume());
            return consumptionRepository.save(newUsage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("Fail to create usage");
        }
    }

    @Transactional()
    public Usage updateConsumption(Dispenser dispenser) {
        Usage consumption=consumptionRepository.findOneWhereOpenAndByDispenser(dispenser.get_id());
        if(consumption.equals(null)){
            throw new NotFound("Usage not found");
        }
        try {
           consumption.setClose_at(new Date(System.currentTimeMillis()));
           long dif = consumption.getClose_at().getTime() - consumption.getOpen_at().getTime();
           int seconds = (int) ( dif*(1.0/1000));
           Double value=(Double) dispenser.getFlow_volume()* seconds;
           consumption.setTotal_spent(value);
           return consumptionRepository.save(consumption);
        } catch (Exception e) {
            log.error("ERROR {}",e);
            throw new InternalError(e.getMessage());
        }

    }
}
