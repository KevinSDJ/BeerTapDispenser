package com.api.beerdispenser.entities;

import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name= "Dispensers")
public class Dispenser implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    @Column(name = "_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(46)")
    private UUID _id;
    private Double flow_amount;
    private String status=Status.CLOSED.getStatus();
    @OneToMany(mappedBy = "dispenser")
    private List<Consumption> usages;
    @OneToOne(mappedBy = "dispenser")
    private Summary summary;
    public Dispenser() {
    }
    public Dispenser(Double flow_amount) {
        this.flow_amount = flow_amount;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
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
    public void setStatus(Status status) {
        this.status = status.getStatus();
    }
    public List<Consumption> getUsages() {
        return usages;
    }
    public void setUsages(List<Consumption> usages) {
        this.usages = usages;
    }
    public Summary getSummary() {
        return summary;
    }
    public void setSummary(Summary summary) {
        this.summary = summary;
    }
    @Override
    public String toString() {
        return "Dispenser [_id=" + _id + ", flow_amount=" + flow_amount + ", status=" + status + ", usages=" + usages
                + ", summary=" + summary + "]";
    }

    
    
    
}
