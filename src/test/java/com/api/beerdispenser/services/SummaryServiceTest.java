package com.api.beerdispenser.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import com.api.beerdispenser.entity.Summary;
import com.api.beerdispenser.services.impl.SummaryServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableAsync
public class SummaryServiceTest {

    @Autowired
    private SummaryServiceImpl summaryServiceImpl;
    private CountDownLatch lock = new CountDownLatch(1);
    
    @Test
    @Order(1)
    void getSummary(){
        
        try{
            lock.await(2000, TimeUnit.MILLISECONDS);
            Summary summary=summaryServiceImpl.getSummary(DispenserServiceTest.getValueTemp());
            System.out.println(summary.toString());
            assertEquals(summary!=null, true,"Expected summary not null");
        }catch(Exception ex){
            ex.printStackTrace();
            fail(ex.getMessage());
        }
       
    }
}
