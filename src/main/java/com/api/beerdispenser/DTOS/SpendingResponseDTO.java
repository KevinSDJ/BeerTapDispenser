package com.api.beerdispenser.DTOS;

import java.util.List;
import com.api.beerdispenser.entities.Consumption;

public record SpendingResponseDTO(Double amount,List<Consumption> usages) {}
