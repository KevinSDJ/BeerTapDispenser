package com.api.beerdispenser.DTOS;


import java.util.Set;
import com.api.beerdispenser.entities.Usage;

public record SpendingResponseDTO(Double amount,Set<Usage> usages) {}
