package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Where(clause = "DELETED = 0")
@Entity(name = "Plane")
@Table(name = "PLANE")
public class Plane extends BaseEntity{

    @JoinTable(name = "FLIGHT_PLANE_RELATION",
            joinColumns = @JoinColumn(name = "PLANE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID"))
    @ManyToMany
    private Set<Flight> flight;

    @NotNull
    @Column(name = "PASSANGER_CAPACITY")
    private Integer passengerCapacity;
}
