package com.api.beerdispenser.dto;


import java.util.Set;
import com.api.beerdispenser.entity.Usage;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record SpendingResponseDTO(Double amount,Set<Usage> usages) {}
