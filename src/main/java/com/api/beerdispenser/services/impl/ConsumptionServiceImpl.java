package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.ConsumptionRepository;

import jakarta.transaction.Transactional;

import com.api.beerdispenser.Exceptions.InternalError;
import com.api.beerdispenser.Exceptions.NotFound;

@Service
@Transactional
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

    public Consumption getInOpen(UUID id){
        Consumption consumption;
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

    public Consumption createConsumption(Dispenser dispenser) {
        try {
            Consumption newUsage = new Consumption(new Date(System.currentTimeMillis()));
            newUsage.setDispenser(dispenser);
            return consumptionRepository.save(newUsage);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("Fail to create usage");
        }
    }

    public Consumption updateConsumption(Dispenser dispenser) {
        Consumption consumption;
        try {
            consumption = consumptionRepository.findOneWhereOpenAndByDispenser(dispenser.get_id());;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError(e.getMessage());
        }
        if(consumption.equals(null)){
            throw new NotFound("Usage not found");
        }
        try {
           consumption.setClose_at(new Date(System.currentTimeMillis()));
           Long dif = consumption.getClose_at().getTime() - consumption.getOpen_at().getTime();
           Integer seconds = (int) ( dif*(1.0/1000));
           consumption.setUsage_amount(dispenser.getFlow_amount()* seconds);
           return consumptionRepository.save(consumption);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }

    }
}
