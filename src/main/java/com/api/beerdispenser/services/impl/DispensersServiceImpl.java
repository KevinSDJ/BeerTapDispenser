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
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

@Service
public class DispensersServiceImpl {
    final Logger log = LoggerFactory.getLogger(DispensersServiceImpl.class);

    @Autowired
    private BeerDispenserRepository dispenserRepository;
    @Autowired
    private UsageServiceImpl usageServiceImpl;

    public Dispenser create(RequestDispenserDTO dispenser) throws BadRequest,InternalServerError{
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

    public List<ResponseDispenserDTO> getAll(){
        try {
            return dispenserRepository.findAll()
            .stream()
            .map(d->new ResponseDispenserDTO(d.get_id(),d.getFlow_volume())).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalError(e.getMessage());
        }
    }

    public Dispenser findById(UUID id) throws RuntimeException{
        Optional<Dispenser> dispenser = null;
        try {
            dispenser = dispenserRepository.findById(id);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalError(e.getMessage());
        }
        if (dispenser.isPresent()) {
            return dispenser.get();
        } else {
            throw new NotFoundException("this dispenser not exist");
        }
    }

    public Dispenser updateState(UUID id, String status) throws RuntimeException {

        Boolean isValidStatus= Status.containValue(status);
        if(!isValidStatus){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad indication,chose open/close");
        }

        Dispenser dispenser = dispenserRepository.findById(id).get();

        if (dispenser==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(dispenser.getStatus().equals(status)){
            throw new ConflictException("Dispenser already "+status);
        }
        dispenser.setStatus(Status.getValue(status));
        try{
            dispenser= usageServiceImpl.updateUsage(dispenser);
        }catch(Exception ex){
            log.error("Error: ", ex.getMessage());
        }

        return dispenser!=null?
        dispenserRepository.save(dispenser)
        :dispenserRepository.findById(id).get();    
    }

    public Boolean exist(UUID id){

        return dispenserRepository.existDispenser(id);
    }

}
