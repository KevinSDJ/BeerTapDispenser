package com.api.beerdispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.api.beerdispenser.entities.Consumption;
import com.api.beerdispenser.entities.Dispenser;
import com.api.beerdispenser.entities.Summary;
import com.api.beerdispenser.repositories.BeerDispenserRepository;
import com.api.beerdispenser.repositories.ConsumptionRepository;
import com.api.beerdispenser.repositories.SummaryRepository;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

class BeerDispenserApplicationTests {

    Store store = new Store();

    @Autowired
    BeerDispenserRepository beerDispenserRepository;
    @Autowired
    SummaryRepository summaryRepository;
    @Autowired
    ConsumptionRepository consumptionRepository;
    
	@Test
    @Order(1)
    public void createDispenser(){
        Dispenser f = new Dispenser(0.080);
        Dispenser dsave= beerDispenserRepository.saveAndFlush(f);
        store.setId(dsave.get_id());
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
    @Test
    @Order(4)
    @Transactional
    public void addConsumption(){
        List<Dispenser> data= beerDispenserRepository.findAll();
        Consumption newUsage = new Consumption(new Date(System.currentTimeMillis()));
        newUsage.setDispenser(data.get(0));
        Consumption usage= consumptionRepository.save(newUsage);

        Optional<Consumption> c= consumptionRepository.findById(1);
        System.out.println(c.get());
        assertEquals(false,usage.equals(null));
    }
    @Test
    @Order(5)
    public void checkUsage(){
       
    }
}


class Store{
    private UUID id;
    public Store(){}
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        System.out.println(this.id);
        this.id = id;
    }
}