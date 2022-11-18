package com.api.beerdispenser.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
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

}
