package com.api.beerdispenser.dto.usage;

import java.util.Date;

public record UsageResponseDTO (Date opend_at,Date closed_at,Double flow_volume,Double total_spent) {
        
}
