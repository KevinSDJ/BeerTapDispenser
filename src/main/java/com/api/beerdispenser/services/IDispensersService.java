package com.api.beerdispenser.services;

import java.util.UUID;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Dispenser;

public interface IDispensersService {
    Dispenser creteDispenser(requestDTO dispenser);
    void deleteDispenser(UUID id);
}
