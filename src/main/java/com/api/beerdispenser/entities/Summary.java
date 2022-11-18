package com.api.beerdispenser.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;


@Entity
@Data
@RequiredArgsConstructor
public class Summary {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull Double total_amount=0.0;
    @OneToOne
    @JoinColumn(name="dispenser_id")
    private Dispenser dispenser;
}
