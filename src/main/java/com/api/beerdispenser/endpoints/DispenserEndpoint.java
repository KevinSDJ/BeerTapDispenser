package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.dto.dispenser.ReqStatusDispenserDTO;
import com.api.beerdispenser.dto.dispenser.RequestDispenserDTO;
import com.api.beerdispenser.dto.dispenser.ResponseDispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Summary;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;
import com.api.beerdispenser.services.impl.SummaryServiceImpl;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/beer-tap-dispenser/")
public class DispenserEndpoint {

    @Autowired
    private DispensersServiceImpl dispensersServiceImpl;
    @Autowired
    private SummaryServiceImpl summaryServiceImpl;

    @PostMapping("/dispensers")
    public ResponseEntity<ResponseDispenserDTO> newDispenser(@RequestBody RequestDispenserDTO dispenser) throws Exception{
        Dispenser s= dispensersServiceImpl.create(dispenser);
        return ResponseEntity.ok( new ResponseDispenserDTO(s.get_id(),s.getFlow_volume()));
    }

    
    @PutMapping("/dispensers/{id}/status")  
    public ResponseEntity<String> generateUsageDispenser(@PathVariable(name = "id") UUID id,@RequestBody ReqStatusDispenserDTO status) throws Exception{
        dispensersServiceImpl.updateState(id, status.status());
        return ResponseEntity.status(202).build();
    }
    @GetMapping("/dispensers/{id}/spending")
    public ResponseEntity<Summary> getSpending(@PathVariable(name="id") UUID id){
        Summary summary=summaryServiceImpl.getSummary(id);
        
        return ResponseEntity.ok(summary);
    }
    
}
