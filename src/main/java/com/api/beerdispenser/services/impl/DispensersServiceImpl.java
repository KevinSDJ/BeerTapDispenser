package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.dto.newDispenser.requestDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Status;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DispensersServiceImpl {
    final Logger log = LoggerFactory.getLogger(DispensersServiceImpl.class);

    @Autowired
    private BeerDispenserRepository dispenserRepository;

    public Dispenser createDispenser(requestDTO dispenser){
        try {
            Dispenser newdispenser = new Dispenser(dispenser.flow_amount());
            return dispenserRepository.save(newdispenser);

        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Dispenser> getAllDispensers() throws ResponseStatusException {
        try {
            return dispenserRepository.findAll();
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Dispenser findOneDispenser(UUID id) throws ResponseStatusException{
        Optional<Dispenser> dispenser = null;
        try {
            dispenser = dispenserRepository.findById(id);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dispenser.isPresent()) {
            return dispenser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Dispenser updateState(UUID id, String status) throws ResponseStatusException {

        try{
            Status.valueOf(status);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad indication");
        }

        if (Status.OPEN.getValue().equals(status)&& isOpen(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Dispenser already open");
        }
        if (Status.CLOSE.getValue().equals(status) && !isOpen(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"dispenser already close");
        }
        Optional<Dispenser> dispenser;
        try {
            dispenser = dispenserRepository.findById(id);
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (dispenser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            dispenser.get().setStatus(Status.valueOf(status).getValue());
            return dispenserRepository.save(dispenser.get());
        }
    }

    public Boolean isOpen(UUID id) {

        return dispenserRepository.isOpen(id);
    }

   
}
