package com.api.beerdispenser.dto.usage;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "Usage")
public record UsageResponseDTO (Date opend_at,Date closed_at,Double flow_volume,Double total_spent) {
        
}
