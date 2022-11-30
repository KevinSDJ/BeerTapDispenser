package com.api.beerdispenser.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data 
@RequiredArgsConstructor
public class Consumption implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;
    @Column(nullable = false)
    private @NonNull Date open_at;
    @Column(nullable = true)
    private Date close_at;
    private Double usage_amount=0.0;
    @ManyToOne()
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
    public Consumption(){}

}
