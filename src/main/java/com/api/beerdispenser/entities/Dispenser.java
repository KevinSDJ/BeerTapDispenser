package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Dispenser implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name="uuid2",strategy = "uuid4")
    @Column(nullable = false)
    private UUID _id;
    @Column(nullable=false)
    private Double flow_volume;
    @Column(nullable = false)
    private String status= "CLOSED";
    @JsonIgnore
    @OneToMany(mappedBy = "dispenser")
    
    private List<Consumption> usage= new ArrayList<>();

    
    public Dispenser(){}
    public Dispenser(Double flow_volume){
        this.flow_volume=flow_volume;
    }
    public UUID get_id() {
        return _id;
    }
    public void set_id(UUID _id) {
        this._id = _id;
    }
    public Double getFlow_volume() {
        return flow_volume;
    }
    public void setFlow_volume(Double flow_volume) {
        this.flow_volume = flow_volume;
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
        return "Dispenser [_id=" + _id + ", flow_volume=" + flow_volume + ", status=" + status + ", usage=" + usage
                + "]";
    }

}
