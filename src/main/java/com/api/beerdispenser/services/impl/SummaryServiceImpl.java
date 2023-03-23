package com.api.beerdispenser.services.impl;

import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Summary;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.repositories.SummaryRepository;
import org.slf4j.Marker;

@Service
public class SummaryServiceImpl {

    private final Logger log = LoggerFactory.getLogger(SummaryServiceImpl.class);
    
    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private DispensersServiceImpl dispensersService;

    public Summary getSummary(UUID id){

        Boolean exist= existSummary(id);
        
        Summary summ=exist?findAndUpdate(id):updateSummary(id);
       
        return summ;
    }

    public Summary updateSummary(UUID id){
        Double total=0.0;
        Dispenser dispenser= dispensersService.findById(id);
        Summary newSummary = new Summary();
        total=dispenser.getUsage().stream()
            .map(e->e.getTotal_spent())
            .reduce((acc,number)->{ return acc+ number;}).get();
        newSummary.setTotal_amount(total);
        newSummary.setDispenser(dispenser);
        return summaryRepository.save(newSummary);
    }

    public Boolean existSummary(UUID id){
        return summaryRepository.existSummary(id);
    }
     
    public Summary findAndUpdate(UUID id){
        Double total=0.0;
        try{
            Summary summary=summaryRepository.findByDispenserId(id);
            total=summary.getDispenser().getUsage().stream()
            .map(e->e.getTotal_spent())
            .reduce((acc,number)->{ return acc+ number;}).get();
            summary.setTotal_amount(total);
            return summaryRepository.save(summary);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalError("Error api process");
        }
    }

    public List<Summary> getAllSummary(){
        return summaryRepository.findAll();
    }
}
