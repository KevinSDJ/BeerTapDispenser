package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name= "Dispensers")
public class Dispenser implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid4",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="uuid2",strategy = "uuid4")
    @Column(nullable = false)
    private UUID _id;
    private Double flow_amount;
    @Column(nullable = false)
    private String status= "CLOSED";
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dispenser",fetch = FetchType.LAZY)
    private List<Consumption> usage;

    
    public Dispenser(){}
    public Dispenser(Double flow_amount){
        this.flow_amount=flow_amount;
    }


    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public Double getFlow_amount() {
        return flow_amount;
    }

    public void setFlow_amount(Double flow_amount) {
        this.flow_amount = flow_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Consumption> getUsage() {
        return usage;
    }

    public void setUsage(List<Consumption> usage) {
        this.usage = usage;
    }

 
    @Override
    public String toString() {
        return "Dispenser [_id=" + _id + ", flow_amount=" + flow_amount + ", status=" + status + ", usages=" 
                + ", summary=" + "]";
    }
}
