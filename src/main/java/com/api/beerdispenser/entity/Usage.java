package com.api.beerdispenser.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usage")
@Schema(hidden = false)
public class Usage implements Serializable{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date open_at;
    @Column(nullable = true)
    private Date close_at;
    @Column(nullable = false)
    private Double flow_volume;
    private Double total_spent=0.0;
    @JsonIgnore
	@ManyToOne
    @JoinColumn(name="dispenser_id", nullable=false)
    private Dispenser dispenser;

    public Usage(Date open_at) {
        this.open_at = open_at;
    }
    public Usage() {
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

    @Override
    public String toString() {
        return "Consumption [_id=" + id + ", open_at=" + open_at + ", close_at=" + close_at + ", flow_volume="
                + flow_volume + ", total_spent=" + total_spent +"]";
    }
}
