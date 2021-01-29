package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Where(clause = "DELETED = 0")
@Entity(name = "FlightRoute")
@Table(name = "FLIGHT_ROUTE")
public class FlightRoute extends BaseEntity{
    public static final long serialVersionUID = 9026906350251779469L;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTURE_AIRPORT_ID")
    private Airport departureAirport;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARRIVAL_AIRPORT_ID")
    private Airport arrivalAirport;

    @NotNull
    @Column(name = "DEPLOYMENT_DATE")
    private Date deploymentDate;

    @NotNull
    @Column(name = "ARRIVAL_DATE")
    private Date arrivalDate;

    @Column(name = "ESTIMATED_FLIGHT_TIME")
    private Double estimatedFlightTime;
}
