package com.api.beerdispenser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.dto.DispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Status;
import com.api.beerdispenser.exception.BadRequest;
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

    public Dispenser createDispenser(DispenserDTO dispenser) throws BadRequest,InternalServerError{
        if(dispenser.flow_volume()==null){
            throw new BadRequest("flow volume required");
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

    public List<DispenserDTO> getAllDispensers(){
        try {
            return dispenserRepository.findAll()
            .stream()
            .map(d->new DispenserDTO(d.get_id(),d.getFlow_volume())).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new InternalError(e.getMessage());
        }
    }

    public Dispenser findOneDispenser(UUID id) throws RuntimeException{
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
