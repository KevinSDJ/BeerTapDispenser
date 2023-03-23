package com.api.beerdispenser.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Collection;
import java.util.UUID;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.api.beerdispenser.dto.dispenser.RequestDispenserDTO;
import com.api.beerdispenser.dto.dispenser.ResponseDispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.entity.Status;
import com.api.beerdispenser.entity.Usage;
import com.api.beerdispenser.exception.BadRequest;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DispenserServiceTest {

    @Autowired
    private DispensersServiceImpl dispensersServiceImpl;
    private static UUID id;

    @Test
    @Order(1)
    void createDispenser(){
        RequestDispenserDTO dispenser=new RequestDispenserDTO(0.021);
        
        Dispenser dispensersave=dispensersServiceImpl.create(dispenser);
        id=dispensersave.get_id();
        assertEquals(
            dispensersave!=null,
            true,
            "Fail , expectated a value type Dispenser class");
    }
    @Test
    @Order(2)
    void createDispenserNotFlowVolumeProvided(){
        RequestDispenserDTO dispenser=new RequestDispenserDTO();
        try{
            dispensersServiceImpl.create(dispenser);
            fail("Expectated NotFoundException");
        }catch(BadRequest ex){
            assertEquals(ex.getMessage(),
            "flow volume price required",
            "Expected this message: flow volume price required");
        }
    }

    @Test
    @Order(3)
    void listDispensers(){
        Collection<ResponseDispenserDTO> dispensers= dispensersServiceImpl.getAll();
        assertEquals(dispensers.size(), 1,"Expected 1 not " + dispensers.size());
    }

    @Test
    @Order(4)
    void findByIdSuccess(){
        Dispenser dispenser= dispensersServiceImpl.findById(id);

        assertNotEquals(dispenser, null, "Expected not to be null");
        
    }
    @Test
    @Order(5)
    void changeStateSuccess(){
        Dispenser dispenser=dispensersServiceImpl.updateState(id, "open");
        assertEquals(dispenser.getStatus(), Status.getValue("open"),"Expected open not"+ dispenser.getStatus());
    }

    @Test
    @Order(6)
    void changeStateFail(){
        try{
            dispensersServiceImpl.updateState(id, "open");
            fail("Expected badRequest exception");
        }catch(BadRequest ex){
            assertEquals(ex.getMessage(), "Dispenser already open","Expected open not "+ex.getMessage());
        }
    
    }

    @Test
    @Order(7)
    void checkUsageExist(){
        Dispenser dispenser= dispensersServiceImpl.findById(id);
        assertEquals(1, dispenser.getUsage().size(),"Excepted 1 usage not "+ dispenser.getUsage().size());
    }

    @Test
    @Order(8)
    void tryClosedUsageCurrentOpen(){
        Dispenser dispenser=dispensersServiceImpl.updateState(id, "close");

        assertEquals(Status.CLOSE.getValue(), dispenser.getStatus(),"Expected close not "+ dispenser.getStatus());
    }

    @Test
    @Order(9)
    void checkClosedUsage(){
        
        Dispenser dispenser = dispensersServiceImpl.findById(id);
        Usage usage= dispenser.getUsage().stream().findFirst().get();
        System.out.println(usage.toString());

        assertEquals(true, usage!=null && usage.getClose_at()!=null,"Expected true usage exist and closed correctly");
        
    }

    
}
