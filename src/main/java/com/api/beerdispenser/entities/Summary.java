package com.api.beerdispenser.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;


@Entity
public class Summary {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Double total_amount=0.0;
    @OneToOne
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;


    public Summary() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getTotal_amount() {
        return this.total_amount;
    }
    public void setTotal_amount(Double total_amount) {
        this.total_amount = this.total_amount+total_amount;
    }
    public Dispenser getDispenser() {
        return this.dispenser;
    }
    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }
   


}
