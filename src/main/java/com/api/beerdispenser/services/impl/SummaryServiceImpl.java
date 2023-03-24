package com.api.beerdispenser.services.impl;

import com.api.beerdispenser.dto.summary.SummaryResponseDTO;
import com.api.beerdispenser.dto.usage.UsageResponseDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Summary;
import com.api.beerdispenser.entity.Usage;
import com.api.beerdispenser.exception.NotFound;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public SummaryResponseDTO getSummary(UUID id) throws RuntimeException{
        Boolean existDispenser= dispensersService.exist(id);
        if(!existDispenser)throw new NotFound("Requested dispenser does not exist");
        Boolean exist= existSummary(id);
        
        Summary summ=exist?findAndUpdate(id):updateSummary(id);
        Collection<UsageResponseDTO> usages= summ.getDispenser().getUsage()
        .stream()
        .map(e-> new UsageResponseDTO(
            e.getOpen_at(), 
            e.getClose_at(), 
            e.getFlow_volume(),
            e.getTotal_spent()))
        .toList();
        SummaryResponseDTO summary= new SummaryResponseDTO(summ.getTotal_amount(),usages);
       
        return summary;
    }

    public Summary updateSummary(UUID id){
        Double total=0.0;
        Optional<Dispenser> dispenser= dispensersService.findById(id);
        Summary newSummary = new Summary();
        for(Usage usage:dispenser.get().getUsage()){
            total+=usage.getTotal_spent();
        }
        newSummary.setTotal_amount(total);
        newSummary.setDispenser(dispenser.get());
        return summaryRepository.save(newSummary);
    }

    public Boolean existSummary(UUID id){
        return summaryRepository.existSummary(id);
    }
     
    public Summary findAndUpdate(UUID id){
        Double total=0.0;
        try{
            Summary summary=summaryRepository.findByDispenserId(id);
            for(Usage usage:summary.getDispenser().getUsage()){
                total+=usage.getTotal_spent();
            }
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
