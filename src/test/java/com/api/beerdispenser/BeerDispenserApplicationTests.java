package com.api.beerdispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Summary;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import com.api.beerdispenser.repositories.SummaryRepository;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

class BeerDispenserApplicationTests {

    @Autowired
    BeerDispenserRepository beerDispenserRepository;
    @Autowired
    SummaryRepository summaryRepository;
    
	@Test
    @Order(1)
    public void createDispenser(){
        Dispenser f = new Dispenser(0.080);
        Dispenser dsave= beerDispenserRepository.saveAndFlush(f);
        assertEquals(false,dsave.equals(null));
    }

    @Test
    @Order(2)
    @Transactional
    public void viewDispensers(){
        List<Dispenser> data= beerDispenserRepository.findAll();
        System.out.println(data);
        assertEquals(1, data.size());
    }
    @Test
    @Order(3)
    @Transactional
    public void addSummary(){
        List<Dispenser> data= beerDispenserRepository.findAll();
        Summary summary = new Summary();
        summary.setDispenser(data.get(0));
        Summary summarysave= summaryRepository.saveAndFlush(summary);
        assertEquals(false,summarysave.equals(null));
    }

    


}
