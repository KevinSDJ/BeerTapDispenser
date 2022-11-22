package com.api.beerdispenser.services;

import java.util.List;
import java.util.UUID;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.projections.DispenserFit;
import com.api.beerdispenser.projections.DispenserFull;

public interface IDispensersService {
    List<DispenserFit> geAllDispensersFit();
    Dispenser createDispenser(requestDTO dispenser);
    List<DispenserFull> getAllDispenserFull();
    Dispenser findOneDispenser(UUID id);
    Dispenser updateState(UUID id, String status);
    Boolean isOpen(UUID id);
}
