package com.api.beerdispenser.dto.newDispenser;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record requestDTO(Double flow_amount) {}