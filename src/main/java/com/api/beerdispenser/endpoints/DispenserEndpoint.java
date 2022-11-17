package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.DTOS.newDispenser.responseDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/beer-tap-dispenser/")
public class DispenserEndpoint {
    
    @Autowired
    DispensersServiceImpl dispensersServiceImpl;

    @PostMapping("/dispenser")
    public ResponseEntity<responseDTO> newDispenser(@RequestBody requestDTO dispenser) throws Exception{
        Dispenser s= dispensersServiceImpl.creteDispenser(dispenser);
        
        return ResponseEntity.ok(new responseDTO(s.get_id(),s.getFlow_amount()));
    }
    

}
