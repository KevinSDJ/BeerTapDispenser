package com.api.beerdispenser.services.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import com.api.beerdispenser.services.IDispensersService;
import com.api.beerdispenser.Exceptions.InternalError;


@Service
public class DispensersServiceImpl implements IDispensersService {

    @Autowired
    BeerDispenserRepository BeerDispenserRepository;

    @Override
    public Dispenser creteDispenser(requestDTO dispenser) throws Exception {
        try{
            Dispenser newdispenser= new Dispenser(dispenser.flow_amount());
            return BeerDispenserRepository.saveAndFlush(newdispenser);

        }catch (Exception e){
            throw new InternalError("fail to create dispenser");
        }
    }

    @Override
    public void deleteDispenser(UUID id) {
        // TODO Auto-generated method stub
        
    }
    
}
