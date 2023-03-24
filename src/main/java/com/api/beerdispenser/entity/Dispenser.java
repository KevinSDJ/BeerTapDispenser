package com.api.beerdispenser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="dispenser")
public class Dispenser implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name="uuid2",strategy = "uuid4")
    @Column(nullable = false)
    private UUID _id;
    private Double flow_volume;
    private String status= "CLOSED";
    @JsonIgnore
    @OneToMany(mappedBy = "dispenser",fetch = FetchType.EAGER)
    private Collection<Usage> usage= new ArrayList<>();

    public Dispenser(){}


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
    public Collection<Usage> getUsage() {
        return usage;
    }
    public void setUsage(Collection<Usage> usage) {
        this.usage = usage;
    }
    @Override
    public String toString() {
        return "Dispenser [_id=" + _id + ", flow_volume=" + flow_volume + ", status=" + status + ", usage=" + usage
                + "]";
    }

}
