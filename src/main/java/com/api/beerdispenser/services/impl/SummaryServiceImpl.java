package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.Exceptions.NotFound;
import com.api.beerdispenser.entities.Usage;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Summary;
import com.api.beerdispenser.repositories.SummaryRepository;


@Service
public class SummaryServiceImpl {

    private final Logger log = LoggerFactory.getLogger(SummaryServiceImpl.class);
    
    @Autowired
    private SummaryRepository summaryRepository;

    public void createSummary(Dispenser dispenser){
        Summary summary = new Summary();
        summary.setDispenser(dispenser);
        
        try{
            summaryRepository.save(summary);
        }catch(Exception e){
            System.out.println(e);
            throw new InternalError("Fail api process");
        }

    }

    public void updateSummary(Usage consumption,UUID dispenserId){
        Summary summary = findSummary(dispenserId);
        if(summary.equals(null)){
            throw new NotFound("Summary not found");
        }
        summary.setTotal_amount(summary.getTotal_amount()+consumption.getTotal_spent());
        Set<Usage> usages=summary.getUsages();
        usages.add(consumption);
        summary.setUsages(usages);
        try{
            summaryRepository.save(summary);
        }catch(Exception error){
            log.error("Error api {}",error);
            throw new InternalError("Fail api process");
        }    
    }

    public Boolean existSummary(UUID id){
        return summaryRepository.existSummary(id);
    }
     
    public Summary findSummary(UUID id){
        try{
            return summaryRepository.findByDispenserId(id);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new InternalError("Error api process");
        }
    }

    public List<Summary> getAllSummary(){
        return summaryRepository.findAll();
    }
}
