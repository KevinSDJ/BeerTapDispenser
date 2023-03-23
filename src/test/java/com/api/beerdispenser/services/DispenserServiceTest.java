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
import com.api.beerdispenser.dto.DispenserDTO;
import com.api.beerdispenser.entity.Dispenser;
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
        DispenserDTO dispenser=new DispenserDTO(0.021);
        
        Dispenser dispensersave=dispensersServiceImpl.createDispenser(dispenser);
        this.id=dispensersave.get_id();
        assertEquals(
            dispensersave!=null,
            true,
            "Fail , expectated a value type Dispenser class");
    }
    @Test
    @Order(2)
    void createDispenserNotFlowVolumeProvided(){
        DispenserDTO dispenser=new DispenserDTO();
        try{
            dispensersServiceImpl.createDispenser(dispenser);
            fail("Expectated NotFoundException");
        }catch(BadRequest ex){
            assertEquals(ex.getMessage(),
            "flow volume required",
            "Expected this message: flow volume required");
        }
    }

    @Test
    @Order(3)
    void listDispensers(){
        Collection<DispenserDTO> dispensers= dispensersServiceImpl.getAllDispensers();
        assertEquals(dispensers.size(), 1,"Expected 1 not " + dispensers.size());
    }

    @Test
    @Order(4)
    void findByIdSuccess(){
        Dispenser dispenser= dispensersServiceImpl.findOneDispenser(id);

        assertNotEquals(dispenser, null, "Expected not to be null");
        
    }

    
}
