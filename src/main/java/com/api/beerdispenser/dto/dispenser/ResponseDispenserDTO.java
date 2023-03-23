package com.api.beerdispenser.dto.dispenser;

import java.util.UUID;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record ResponseDispenserDTO(UUID id , Double flow_volume) {
}
