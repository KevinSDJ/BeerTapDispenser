package com.api.beerdispenser.dto;

import java.util.UUID;

public record DispenserDTO(UUID id ,Double flow_volume,String status) {
    
    public DispenserDTO(UUID id,Double flow_volume){
        this(id,flow_volume,null);
    }
    public DispenserDTO(Double flow_volume){
        this(null,flow_volume,null);
    }
    public DispenserDTO(String status){
        this(null,null,status);
    }
    public DispenserDTO(){
        this(null,null,null);
    }
}
