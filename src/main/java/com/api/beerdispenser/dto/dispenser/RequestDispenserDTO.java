package com.api.beerdispenser.dto.dispenser;

public record RequestDispenserDTO (Double flow_volume){
    
    public RequestDispenserDTO(){
        this(null);
    }
}
