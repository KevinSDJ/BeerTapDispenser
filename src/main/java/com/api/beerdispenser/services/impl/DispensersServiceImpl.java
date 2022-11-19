package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.projections.DispenserFit;
import com.api.beerdispenser.projections.DispenserFull;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import com.api.beerdispenser.services.IDispensersService;
import com.api.beerdispenser.Exceptions.InternalError;
import com.api.beerdispenser.Exceptions.NotFound;


@Service
@Transactional
public class DispensersServiceImpl implements IDispensersService {
    final Logger log = LoggerFactory.getLogger(DispensersServiceImpl.class);

    @Autowired
    BeerDispenserRepository dispenserRepository;

    @Override
    public Dispenser createDispenser(requestDTO dispenser){
        try{
            Dispenser newdispenser=  new Dispenser(dispenser.flow_amount());
            
            return dispenserRepository.save(newdispenser);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new InternalError("fail to create dispenser");
        }
    }
    @Override
    public List<DispenserFit> geAllDispensersFit()  {
        try{
            return dispenserRepository.findAllFit();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new InternalError("fail fetch dispensers");
        }
        
    }
    @Override
    public List<DispenserFull> getAllDispenserFull() {
        
        return dispenserRepository.findAllFull();
    }
    @Override
    public Dispenser findOneDispenser(UUID id) {
            Optional<Dispenser> dispenser=null;
            try{
                dispenser= dispenserRepository.findById(id);
            }catch(Exception e){
                log.error(e.getMessage());
                throw new InternalError("Fail api Transaction");
            }
            if(dispenser.isPresent()){
                return dispenser.get();
            }else{
                throw new NotFound("Dispenser not Match");
            }
    }
    
}
