package com.api.beerdispenser.entities;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Summary {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final Double total_amount=0.0;
    @OneToOne
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    @OneToMany()
    private Set<Consumption> usages= new HashSet<>();
    
    public Summary(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public Dispenser getDispenser() {
        return dispenser;
    }

    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }

    public Set<Consumption> getUsages() {
        return usages;
    }

    public void setUsages(Set<Consumption> usages) {
        this.usages = usages;
    }

    @Override
    public String toString() {
        return "Summary [id=" + id + ", total_amount=" + total_amount + ", usages="
                + usages + "]";
    }

   

}
