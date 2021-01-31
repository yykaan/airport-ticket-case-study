package com.kaan.airportt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "FLIGHT")
public class Flight extends BaseEntity {

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="AIRLINE_ID", nullable=false)
    private Airline airline;

    @OneToMany(mappedBy="flight")
    private Set<FlightTicket> flightTickets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ROUTE_ID", referencedColumnName = "ID")
    private FlightRoute flightRoute;

    @Column(name = "PASSENGER_CAPACITY")
    private Integer passengerCapacity;

    @Column(name = "BASE_PRICE")
    private BigDecimal basePrice;

}
