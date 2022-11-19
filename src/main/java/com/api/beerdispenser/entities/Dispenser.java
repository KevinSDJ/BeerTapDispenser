package com.api.beerdispenser.entities;

import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name= "Dispensers")
@Data 
@RequiredArgsConstructor 
public class Dispenser implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    @Column(name = "_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(46)")
    private UUID _id;
    private @NonNull Double flow_amount;
    private String status=Status.CLOSED.getStatus();
    @OneToMany(mappedBy = "dispenser",fetch = FetchType.EAGER)
    private List<Consumption> usages;
    @OneToOne(mappedBy = "dispenser")
    private Summary summary;
    public Dispenser(){}
 
}
