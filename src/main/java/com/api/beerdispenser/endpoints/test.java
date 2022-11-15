package com.api.beerdispenser.endpoints;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test {
    St n = new St();

    @GetMapping("/")
    public ResponseEntity<String> testApi(){
        if(n.before!= null&&n.init!=null){
            Long dif = new Date(n.before).getTime()- new Date(n.init).getTime();
            Integer seconds = (int) ( dif*(1.0/1000));
            System.out.println(seconds);
            n.init=null;
            n.before=null;
            return ResponseEntity.ok("seconds: "+seconds);
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