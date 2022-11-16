package com.api.beerdispenser.entities;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name= "Dispensers")
public class Dispenser {
    
    public Dispenser() {
    }
    @Id
    @GeneratedValue
    private UUID _id;
    private Double flow_amount;
    private Status status=Status.CLOSED;
    @OneToMany
    @JoinColumn(name= "id_dispenser")
    private List<Consumption> usage;


    public Dispenser(Double flow_amount) {
        this.flow_amount = flow_amount;
    }
    
    public UUID get_id() {
        return this._id;
    }
    public void set_id(UUID _id) {
        this._id = _id;
    }
    public Double getFlow_amount() {
        return this.flow_amount;
    }
    public void setFlow_amount(Double flow_amount) {
        this.flow_amount = flow_amount;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public List<Consumption> getUsage() {
        return this.usage;
    }
    public void setUsage(List<Consumption> usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Dispenser [_id=" + _id + ", flow_amount=" + flow_amount + ", status=" + status + ", usage=" + usage
                + "]";
    }
}
