package com.api.beerdispenser.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import jakarta.transaction.Transactional;

import com.api.beerdispenser.Exceptions.BadRequest;
import com.api.beerdispenser.Exceptions.InternalError;
import com.api.beerdispenser.Exceptions.NotFound;

@Service
@Transactional
public class DispensersServiceImpl {
    final Logger log = LoggerFactory.getLogger(DispensersServiceImpl.class);

    @Autowired
    private BeerDispenserRepository dispenserRepository;

    public Dispenser createDispenser(requestDTO dispenser) {
        try {
            Dispenser newdispenser = new Dispenser(dispenser.flow_amount());

            return dispenserRepository.save(newdispenser);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("fail to create dispenser");
        }
    }

    public List<Dispenser> getAllDispensers() {
        try {
            return dispenserRepository.findAll();
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }
    }

    public Dispenser findOneDispenser(UUID id) {
        Optional<Dispenser> dispenser = null;
        try {
            dispenser = dispenserRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError("Fail api Transaction");
        }
        if (dispenser.isPresent()) {
            return dispenser.get();
        } else {
            throw new NotFound("Dispenser not found");
        }
    }

    public Dispenser updateState(UUID id, String status) {

        String[] statusvalues = { "OPEN", "CLOSED" };
        boolean contains = Arrays.stream(statusvalues).anyMatch(status::equals);

        if (!contains) {
            throw new BadRequest("Mmm nop,refresh your memory");
        }
        if (status.toUpperCase().equals("OPEN") && isOpen(id)) {
            throw new BadRequest("dispenser already open");
        }
        if (status.toUpperCase().equals("CLOSED") && !isOpen(id)) {
            throw new BadRequest("dispenser already closed");
        }
        Optional<Dispenser> dispenser;
        try {
            dispenser = dispenserRepository.findById(id);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }
        if (dispenser.isEmpty()) {
            throw new NotFound("Dispenser not found");
        } else {
            dispenser.get().setStatus(status);
            return dispenserRepository.save(dispenser.get());
        }
    }

    public Boolean isOpen(UUID id) {

        return dispenserRepository.isOpen(id);
    }

}
