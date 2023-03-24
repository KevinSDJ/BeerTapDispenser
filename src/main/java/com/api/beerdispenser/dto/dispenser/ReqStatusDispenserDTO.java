package com.api.beerdispenser.dto.dispenser;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title="Dispenser Status")
public record ReqStatusDispenserDTO (String status){
    
}
