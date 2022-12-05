package com.api.beerdispenser.endpoints;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.entities.Consumption;
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
    public ResponseEntity<List<Consumption>> getUsages() throws Exception{
        List<Consumption> usages= consumptionServiceImpl.listAllUsages();
        System.out.println(usages);
        return ResponseEntity.ok(usages);
    }
   
}

