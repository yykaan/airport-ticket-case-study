package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "FLIGHT_ROUTE")
public class FlightRoute extends BaseEntity{

    @JsonIgnore
    @OneToOne(mappedBy = "flightRoute")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name="ARRIVAL_AIRPORT_ID", nullable=false)
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name="DEPARTURE_AIRPORT_ID", nullable=false)
    private Airport departureAirport;
}
