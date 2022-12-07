package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Summary implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;
    private Double total_amount=0.0;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    @OneToMany(mappedBy = "summary")
    private Set<Usage> usages= new HashSet<>();
    
    public Summary(){}

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    public Set<Usage> getUsages() {
        return usages;
    }

    public void setUsages(Set<Usage> usages) {
        this.usages = usages;
    }

    @Override
    public String toString() {
        return "Summary [_id=" + _id + ", total_amount=" + total_amount + ", dispenser=" + dispenser + ", usages="
                + usages + "]";
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

}
