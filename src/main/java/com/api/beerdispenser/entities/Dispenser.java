package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "Dispensers")
public class Dispenser implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    @Column(name = "_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(46)")
    private UUID _id;
    private Double flow_amount;
    @Column(name = "status",nullable = false)
    private String status= "CLOSED";
   
    @OneToOne(mappedBy = "dispenser")
    private Summary summary;
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

    

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Dispenser [_id=" + _id + ", flow_amount=" + flow_amount + ", status=" + status + ", usages=" 
                + ", summary=" + summary + "]";
    }

 
}
