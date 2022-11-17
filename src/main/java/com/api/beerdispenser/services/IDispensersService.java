package com.api.beerdispenser.services;

import java.util.List;
import java.util.UUID;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;

public interface IDispensersService {
    List<Dispenser> geAllDispensersFit();
    Dispenser createDispenser(requestDTO dispenser);
    void deleteDispenser(UUID id);
}
