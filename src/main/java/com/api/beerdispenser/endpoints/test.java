package com.api.beerdispenser.endpoints;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.beerdispenser.DTOS.newDispenser.requestDTO;
import com.api.beerdispenser.entities.Usage;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.services.impl.ConsumptionServiceImpl;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;


@RestController
@RequestMapping("/test/")
public class test {

    @Autowired
    private DispensersServiceImpl dispensersServiceImpl;
    @Autowired
    private ConsumptionServiceImpl consumptionServiceImpl;
   
    @GetMapping("/dispensers")
    public ResponseEntity<List<Dispenser>> getFull() {
        List<Dispenser> d=dispensersServiceImpl.getAllDispensers();
        
        return ResponseEntity.ok(d);
    }
    @GetMapping("/usages")
    public ResponseEntity<List<Usage>> getUsages() throws Exception{
        List<Usage> usages= consumptionServiceImpl.listAllUsages();
        return ResponseEntity.ok(usages);
    }
    @PostMapping("/addusage")
    public ResponseEntity<Usage> addusage (){
        Dispenser dispenser = dispensersServiceImpl.createDispenser(new requestDTO(0.542));
        Usage consumption = consumptionServiceImpl.createConsumption(dispenser);
        return ResponseEntity.ok(consumption);
    }
   
}

