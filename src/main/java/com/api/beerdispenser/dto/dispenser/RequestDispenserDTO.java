package com.api.beerdispenser.dto.dispenser;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record RequestDispenserDTO (Double flow_volume){
    
    public RequestDispenserDTO(){
        this(null);
    }
}
