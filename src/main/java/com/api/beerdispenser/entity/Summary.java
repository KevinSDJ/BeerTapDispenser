package com.api.beerdispenser.entity;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="summary")
public class Summary implements Serializable{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Double total_amount=0.0;
    @CreationTimestamp
    private Date last_update;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    
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

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Dispenser getDispenser() {
        return dispenser;
    }

    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }
    public Date getLast_update(){
        return last_update;
    }

    @Override
    public String toString() {
        return "Summary [id=" + id + ", total_amount=" + total_amount + ", dispenser=" + dispenser +"]";
    }


}
