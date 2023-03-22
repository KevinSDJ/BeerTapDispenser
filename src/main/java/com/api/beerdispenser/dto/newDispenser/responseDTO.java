package com.api.beerdispenser.dto.newDispenser;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record responseDTO(UUID _id,Double flow_amount) {}
