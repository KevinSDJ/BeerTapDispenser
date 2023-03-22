package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.beerdispenser.entity.Usage;
import com.api.beerdispenser.entity.Dispenser;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.api.beerdispenser.repositories.UsageRepository;


@Service
public class UsageServiceImpl {

    private final Logger log = LoggerFactory.getLogger(UsageServiceImpl.class);
    @Autowired
    private UsageRepository consumptionRepository;

    public List<Usage> listAllUsages() {

        try {
            return consumptionRepository.findAll();
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
    public List<Usage> listAllByDispenserId(UUID id){
        List<Usage> usages;
        try{
            usages = consumptionRepository.findByDispenserId(id);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
        if(usages.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usages;
    }

    public Usage getInOpen(UUID id){
        Usage consumption;
        try{
            consumption = consumptionRepository.findOneWhereOpenAndByDispenser(id);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info(consumption.toString());
        if(consumption.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return consumption;
    }
    
   
    public Usage createConsumption(Dispenser dispenser) {
        try {
            Usage newUsage = new Usage(new Date(System.currentTimeMillis()));
            //newUsage.setDispenser(dispenser);
            newUsage.setFlow_volume(dispenser.getFlow_volume());
            return consumptionRepository.save(newUsage);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional()
    public Usage updateConsumption(Dispenser dispenser) {
        Usage consumption=consumptionRepository.findOneWhereOpenAndByDispenser(dispenser.get_id());
        if(consumption.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        try {
           consumption.setClose_at(new Date(System.currentTimeMillis()));
           long dif = consumption.getClose_at().getTime() - consumption.getOpen_at().getTime();
           int seconds = (int) ( dif*(1.0/1000));
           Double value=(Double) dispenser.getFlow_volume()* seconds;
           consumption.setTotal_spent(value);
           return consumptionRepository.save(consumption);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
