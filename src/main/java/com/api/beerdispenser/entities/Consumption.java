package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Consumption implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;
    @Column(nullable = true)
    private Date open_at;
    @Column(nullable = true)
    private Date close_at;
    private Double usage_amount=0.0;
    @ManyToOne(cascade = CascadeType.ALL,optional = false ,fetch = FetchType.LAZY)
    @MapsId("_id")
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    public Consumption(Date open_at) {
        this.open_at = open_at;
    }
    public Consumption() {
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
    public Double getUsage_amount() {
        return usage_amount;
    }
    public void setUsage_amount(Double usage_amount) {
        this.usage_amount = usage_amount;
    }
    public Dispenser getDispenser() {
        return dispenser;
    }
    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }
    @Override
    public String toString() {
        return "Consumption [_id=" + _id + ", open_at=" + open_at + ", close_at=" + close_at + ", usage_amount="
                + usage_amount + ", dispenser=" + dispenser + "]";
    }



}
