package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.dto.dispenser.RequestDispenserDTO;
import com.api.beerdispenser.dto.dispenser.ResponseDispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Status;
import com.api.beerdispenser.exception.BadRequest;
import com.api.beerdispenser.exception.ConflictException;
import com.api.beerdispenser.exception.InternalServerError;
import com.api.beerdispenser.exception.NotFound;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import org.slf4j.Marker;

@Service
public class DispensersServiceImpl {
    final Logger log = LoggerFactory.getLogger(DispensersServiceImpl.class);

    @Autowired
    private BeerDispenserRepository dispenserRepository;
    @Autowired
    private UsageServiceImpl usageServiceImpl;

    public Dispenser create(RequestDispenserDTO dispenser)throws RuntimeException{
        if(dispenser.flow_volume()==null){
            throw new BadRequest("flow volume price required");
        }

        try {
            Dispenser newdispenser = new Dispenser();
            newdispenser.setFlow_volume(dispenser.flow_volume());
            return dispenserRepository.save(newdispenser);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error ",e.getMessage());
            throw new InternalServerError();
        }
    }

    public List<ResponseDispenserDTO> getAll() throws RuntimeException{
        try {
            return dispenserRepository.findAll()
            .stream()
            .map(d->new ResponseDispenserDTO(d.get_id(),d.getFlow_volume())).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalServerError();
        }
    }

    public Optional<Dispenser> findById(UUID id){
    
        return dispenserRepository.findById(id);
      
    }

    public Dispenser updateState(UUID id, String status) throws RuntimeException {

        Boolean isValidStatus= Status.containValue(status);
        if(!isValidStatus){
            throw new BadRequest("Bad indication,chose open/close");
        }

        Optional<Dispenser> dispenser = dispenserRepository.findById(id);

        if (dispenser.isEmpty()){
            throw new NotFound("Requested dispenser does not exist");
        };

        if(dispenser.get().getStatus().equals(status)){
            throw new ConflictException("Dispenser already "+status);
        }
        dispenser.get().setStatus(Status.getValue(status));
        try{
            dispenser= Optional.of(usageServiceImpl.updateUsage(dispenser.get()));
        }catch(Exception ex){
            log.error("Error: ", ex.getMessage());
        }

        return dispenser!=null?
        dispenserRepository.save(dispenser.get())
        :dispenserRepository.findById(id).get();    
    }

    public Boolean exist(UUID id){

        return dispenserRepository.existDispenser(id);
    }

}
