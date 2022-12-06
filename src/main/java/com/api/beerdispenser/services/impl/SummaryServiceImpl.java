package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Summary;
import com.api.beerdispenser.repositories.SummaryRepository;

@Service
public class SummaryServiceImpl {

    private final Logger log = LoggerFactory.getLogger(SummaryServiceImpl.class);
    
    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private DispensersServiceImpl dispenserServiceImpl;
    @Autowired
    private ConsumptionServiceImpl consumptionServiceImpl;

    public void createSummary(UUID id){
        Dispenser dispenser;
        try{
            dispenser = dispenserServiceImpl.findOneDispenser(id);
        }catch(Exception e){
            throw new InternalError("Fail api process");
        }
        Summary newSummary = new Summary();
        newSummary.setDispenser(dispenser);
        List<Consumption> consumption = consumptionServiceImpl.listAllByDispenserId(id);
        Double amount=0.0;
        for(Consumption cons : consumption){
            newSummary.getUsages().add(cons);
            amount+= cons.getTotal_spent();
        }
        try{
            summaryRepository.save(newSummary);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new InternalError("Fail api process");
        }

    }

    public void updateSummary(Consumption consumption){
        
        if(existSummary(consumption.getDispenser().get_id())){
            System.out.println("existe sumario");
        }else {
            System.out.println("no existe sumario");
        }
    }
    public Boolean existSummary(UUID id){
        return summaryRepository.existSummary(id);
    }
     
    public Summary findSummary(UUID dispenser_id){
        try{
            return summaryRepository.findByDispenserId(dispenser_id);
        }catch(Exception e){
            throw new InternalError("Error api process");
        }
    }
}
