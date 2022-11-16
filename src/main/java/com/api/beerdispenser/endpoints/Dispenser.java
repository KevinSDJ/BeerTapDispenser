package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/beer-tap-dispenser/")
public class Dispenser {
    
    @PostMapping("/dispenser")
    public ResponseEntity<String> newDispenser(@RequestBody requestDTO dispenser){
        
        return ResponseEntity.ok(dispenser.toString());
    }

}
