package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import com.api.beerdispenser.services.IDispensersService;
import com.api.beerdispenser.Exceptions.InternalError;


@Service
@Transactional
public class DispensersServiceImpl implements IDispensersService {

    @Autowired
    BeerDispenserRepository BeerDispenserRepository;

    @Override
    public Dispenser createDispenser(requestDTO dispenser){
        try{
            Dispenser newdispenser= new Dispenser(dispenser.flow_amount());
            return BeerDispenserRepository.save(newdispenser);

        }catch (Exception e){
            throw new InternalError("fail to create dispenser");
        }
    }

    @Override
    public void deleteDispenser(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Dispenser> geAllDispensersFit()  {
        try{
            return BeerDispenserRepository.findAll();
        }catch (Exception e){
            throw new InternalError("fail fetch dispensers");
        }
        
    }
    
}
