package com.api.beerdispenser.endpoints;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.services.impl.ConsumptionServiceImpl;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;



@RestController
public class test {
    St n = new St();
    @Autowired
    private DispensersServiceImpl dispensersServiceImpl;
    @Autowired
    private ConsumptionServiceImpl consumptionServiceImpl;

    @GetMapping("/")
    public ResponseEntity<String> testApi(){
        if(n.before!= null&&n.init!=null){
            Long dif = new Date(n.before).getTime()- new Date(n.init).getTime();
            Integer seconds = (int) ( dif*(1.0/1000));
            System.out.println(seconds);
            n.init=null;
            n.before=null;
            Double exampleprice= 0.080;
            return ResponseEntity.ok("seconds: "+seconds+", price: "+(exampleprice*seconds));
        }
        if(n.before==null&& n.init!=null){
            n.before= System.currentTimeMillis();
        }
        if(n.init==null){
            n.init= System.currentTimeMillis();
        }
        System.out.println("first request: "+n.viewInit());
        System.out.println("second request: "+n.viewBefore());
        return ResponseEntity.ok("registry");
    }
    @GetMapping("/random-uuid")
    public ResponseEntity<List<Dispenser>> getFull() {
        List<Dispenser> d=dispensersServiceImpl.getAllDispensers();
        System.out.println(d);
        return ResponseEntity.ok(d);
    }
    @GetMapping("/usages")
    public ResponseEntity<List<Consumption>> getUsages() throws Exception{
        List<Consumption> usages= consumptionServiceImpl.listAllUsages();
        System.out.println(usages);
        return ResponseEntity.ok(usages);
    }
    @PostMapping("/usages")
    public ResponseEntity<Consumption> postUsage() throws Exception{
        Consumption consumption = consumptionServiceImpl.createConsumption();
        return ResponseEntity.ok(consumption);
    }
}


class St{
    Long init=null;
    Long before=null;

    public Date viewInit(){
        
        if(init==null){
            return null;
        }
        return new Date(init);
    }
    public Date viewBefore(){
        if(before==null){
            return null;
        }
        return new Date(before);
    }
}