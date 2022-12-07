package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.beerdispenser.DTOS.SpendingResponseDTO;
import com.api.beerdispenser.DTOS.StatusRequestDTO;
import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.DTOS.newDispenser.responseDTO;
import com.api.beerdispenser.entities.Usage;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Summary;

import com.api.beerdispenser.services.impl.ConsumptionServiceImpl;
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
    private ConsumptionServiceImpl consumptionServiceImpl;
    @Autowired
    private SummaryServiceImpl summaryServiceImpl;

    @PostMapping("/dispenser")
    public ResponseEntity<responseDTO> newDispenser(@RequestBody requestDTO dispenser) throws Exception{
        Dispenser s= dispensersServiceImpl.createDispenser(dispenser);
        summaryServiceImpl.createSummary(s);
        return ResponseEntity.ok(new responseDTO(s.get_id(),s.getFlow_volume()));
    }
    @PutMapping("/dispenser/{id}/status")  
    public ResponseEntity<String> generateUsageDispenser(@PathVariable(name = "id") UUID id,@RequestBody StatusRequestDTO status) throws Exception{
        Dispenser dispenser = dispensersServiceImpl.updateState(id, status.status());
        Usage consumption=null;
        if(dispenser.getStatus().equals("OPEN")){
            consumptionServiceImpl.createConsumption(dispenser);
        }
        if(dispenser.getStatus().equals("CLOSED")){
            consumption =consumptionServiceImpl.updateConsumption(dispenser);
            summaryServiceImpl.updateSummary(consumption, id);
        }
        
        
        return ResponseEntity.ok("Success");
    }
    @GetMapping("/dispenser/{id}/spending")
    public ResponseEntity<SpendingResponseDTO> getSpending(@PathVariable(name="id") UUID id){
        Summary summary=summaryServiceImpl.findSummary(id);
        SpendingResponseDTO spending= new SpendingResponseDTO(summary.getTotal_amount(),summary.getUsages());
        return ResponseEntity.ok(spending);
    }
    
}
