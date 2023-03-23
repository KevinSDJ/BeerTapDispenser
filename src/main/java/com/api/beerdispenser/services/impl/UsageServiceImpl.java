package com.api.beerdispenser.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.beerdispenser.entity.Usage;
import com.api.beerdispenser.exception.InternalServerError;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Status;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.api.beerdispenser.repositories.UsageRepository;


@Service
public class UsageServiceImpl {

    private final Logger log = LoggerFactory.getLogger(UsageServiceImpl.class);
    @Autowired
    private UsageRepository usageRepository;

    public List<Usage> listAllUsages() {

        try {
            return usageRepository.findAll();
        } catch (Exception e) {
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
    public List<Usage> listAllByDispenserId(UUID id){
        List<Usage> usages;
        try{
            usages = usageRepository.findByDispenserId(id);
        }catch(Exception e){
            log.error(Marker.ANY_MARKER, "Error {}",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
        if(usages.equals(null)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usages;
    }

    public Dispenser updateUsage(Dispenser dispenser) throws RuntimeException {
        
        Usage usage= null;
        if(dispenser.getStatus().equals(Status.OPEN.getValue())){
            usage = new Usage();
            usage.setFlow_volume(dispenser.getFlow_volume());
            usage.setOpen_at(new Date(System.currentTimeMillis()));
            dispenser.getUsage().add(usage);
            usage.setDispenser(dispenser);
            try{
                usageRepository.save(usage);
            }catch(Exception ex){
                ex.printStackTrace();
                throw new InternalServerError();
            }
            return dispenser;
        }else{
            usage= usageRepository.findOneWhereOpenAndByDispenser(dispenser.get_id());
            dispenser.getUsage().remove(usage);
            usage.setClose_at(new Date(System.currentTimeMillis()+3300));
            long dif = usage.getClose_at().getTime() - usage.getOpen_at().getTime();
            int seconds = (int) ( dif*(1.0/1000));
            usage.setTotal_spent( (Double) (dispenser.getFlow_volume()* seconds));
            try{
                dispenser.getUsage().add(usageRepository.saveAndFlush(usage));
            }catch(Exception ex){
                ex.printStackTrace();
                throw new InternalServerError();
            }
            return dispenser;
        }
    }
}
