package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.DTOS.StatusRequestDTO;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.DTOS.newDispenser.responseDTO;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.services.impl.ConsumptionServiceImpl;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/beer-tap-dispenser/")
public class DispenserEndpoint {

    @Autowired
    DispensersServiceImpl dispensersServiceImpl;

    @Autowired
    ConsumptionServiceImpl consumptionServiceImpl;

    @PostMapping("/dispenser")
    public ResponseEntity<responseDTO> newDispenser(@RequestBody requestDTO dispenser) throws Exception{
        Dispenser s= dispensersServiceImpl.createDispenser(dispenser);
        
        return ResponseEntity.ok(new responseDTO(s.get_id(),s.getFlow_amount()));
    }
    @PutMapping("/dispenser/{id}/status")
    public ResponseEntity<Dispenser> getAllDipenserFitData(@PathVariable(name = "id") UUID id,@RequestBody StatusRequestDTO status) throws Exception{
        Dispenser dispenser=dispensersServiceImpl.updateState(id, status.status());
        return ResponseEntity.ok(dispenser);
    }
    
}
