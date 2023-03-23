package com.api.beerdispenser.dto.dispenser;

import java.util.UUID;

public record ResponseDispenserDTO(UUID id , Double flow_volume) {
}
