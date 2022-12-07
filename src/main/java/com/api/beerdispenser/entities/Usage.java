package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Usage implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;
    @Column(nullable = false)
    private Date open_at;
    @Column(nullable = true)
    private Date close_at;
    private Double flow_volume;
    private Double total_spent=0.0;
    @ManyToOne()
    @JoinColumn(name="dispenser_id")
    @JsonIgnore
    private Dispenser dispenser;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name="summary_id")
    private Summary summary;
    

    public Usage(Date open_at) {
        this.open_at = open_at;
    }
    public Usage() {
    }
    public Long get_id() {
        return _id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public Date getOpen_at() {
        return open_at;
    }
    public void setOpen_at(Date open_at) {
        this.open_at = open_at;
    }
    public Date getClose_at() {
        return close_at;
    }
    public void setClose_at(Date close_at) {
        this.close_at = close_at;
    }
    public Double getFlow_volume() {
        return flow_volume;
    }
    public void setFlow_volume(Double flow_volume) {
        this.flow_volume = flow_volume;
    }
    public Double getTotal_spent() {
        return total_spent;
    }
    public void setTotal_spent(Double total_spent) {
        this.total_spent = total_spent;
    }
    public Dispenser getDispenser() {
        return dispenser;
    }
    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }
    public Summary getSummary() {
        return summary;
    }
    public void setSummary(Summary summary) {
        this.summary = summary;
    }
    @Override
    public String toString() {
        return "Consumption [_id=" + _id + ", open_at=" + open_at + ", close_at=" + close_at + ", flow_volume="
                + flow_volume + ", total_spent=" + total_spent + ", dispenser=" + dispenser + ", summary=" + summary
                + "]";
    }

}
