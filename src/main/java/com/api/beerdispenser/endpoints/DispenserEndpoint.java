package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.dto.DispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Summary;
import com.api.beerdispenser.entity.Usage;
import com.api.beerdispenser.services.impl.UsageServiceImpl;
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
    private UsageServiceImpl consumptionServiceImpl;
    @Autowired
    private SummaryServiceImpl summaryServiceImpl;

    @PostMapping("/dispensers")
    public ResponseEntity<DispenserDTO> newDispenser(@RequestBody DispenserDTO dispenser) throws Exception{
        System.out.println(dispenser.flow_volume());
        Dispenser s= dispensersServiceImpl.createDispenser(dispenser);
        return ResponseEntity.ok( new DispenserDTO(s.get_id(),s.getFlow_volume()));
    }

    
    @PutMapping("/dispensers/{id}/status")  
    public ResponseEntity<String> generateUsageDispenser(@PathVariable(name = "id") UUID id,@RequestBody DispenserDTO status) throws Exception{
        Dispenser dispenser = dispensersServiceImpl.updateState(id, status.status());
        Usage consumption=null;
        if(dispenser.getStatus().equals("OPEN")){
            consumptionServiceImpl.createConsumption(dispenser);
        }
        if(dispenser.getStatus().equals("CLOSED")){
            consumption =consumptionServiceImpl.updateConsumption(dispenser);
            summaryServiceImpl.updateSummary(consumption, id);
        }
        
        
        return ResponseEntity.status(202).build();
    }
    @GetMapping("/dispensers/{id}/spending")
    public ResponseEntity<Summary> getSpending(@PathVariable(name="id") UUID id){
        Summary summary=summaryServiceImpl.findSummary(id);
        
        return ResponseEntity.ok(summary);
    }
    
}
