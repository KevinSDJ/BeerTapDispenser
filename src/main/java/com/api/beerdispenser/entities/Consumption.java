package com.api.beerdispenser.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Consumption {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;
    @Column(nullable = true)
    private Date open_at;
    @Column(nullable = true)
    private Date close_at;
    @ManyToOne()
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    
    public Consumption(Date open_at) {
        this.open_at = open_at;
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
    @Override
    public String toString() {
        return "Consumption [_id=" + _id + ", open_at=" + open_at + ", close_at=" + close_at + "]";
    }
    
    
}
