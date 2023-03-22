package com.api.beerdispenser.services.impl;

import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Summary;
import com.api.beerdispenser.entity.Usage;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.repositories.SummaryRepository;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


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
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void updateSummary(Usage consumption,UUID dispenserId){
        Summary summary = findSummary(dispenserId);
        if(summary.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        summary.setTotal_amount(summary.getTotal_amount()+consumption.getTotal_spent());
        Collection<Usage> usages=summary.getDispenser().getUsage();
        usages.add(consumption);
        summary.setDispenser(summary.getDispenser());;
        try{
            summaryRepository.save(summary);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }    
    }

    public Boolean existSummary(UUID id){
        return summaryRepository.existSummary(id);
    }
     
    public Summary findSummary(UUID id){
        try{
            return summaryRepository.findByDispenserId(id);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalError("Error api process");
        }
    }

    public List<Summary> getAllSummary(){
        return summaryRepository.findAll();
    }
}
