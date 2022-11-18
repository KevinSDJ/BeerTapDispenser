package com.api.beerdispenser.services;

import java.util.List;

import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.projections.DispenserFit;
import com.api.beerdispenser.projections.DispenserFull;

public interface IDispensersService {
    List<DispenserFit> geAllDispensersFit();
    Dispenser createDispenser(requestDTO dispenser);
    List<DispenserFull> getAllDispenserFull();
}
