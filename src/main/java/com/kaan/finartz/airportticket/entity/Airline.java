package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Airline")
@Table(name = "AIRLINE")
public class Airline extends BaseEntity{
    private static final long serialVersionUID = 2933805674271485237L;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @JoinTable(name = "AIRLINE_AIRPORT_RELATION",
            joinColumns = @JoinColumn(name = "AIRLINE_ID"),
            inverseJoinColumns = @JoinColumn(name = "AIRPORT_ID"))
    @ManyToMany
    private List<Airport> airports;

    @OneToMany(
            mappedBy = "airline",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Flight> flightList;

    public void addFlight(Flight flight){
        if(flightList.isEmpty()){
            flightList = new ArrayList<>();
        }
        flightList.add(flight);
        flight.setAirline(this);
    }

    public void removeFlight(Flight flight){
        if(flightList.isEmpty()){
            flightList = new ArrayList<>();
        }else {
            flightList.remove(flight);
            flight.setAirline(null);
        }
    }

}
