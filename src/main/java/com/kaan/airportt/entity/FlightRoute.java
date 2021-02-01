package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "FLIGHT_ROUTE")
@ApiModel(value = "FlightRoute Entity Documentation", description = "Entity")
public class FlightRoute extends BaseEntity{

    @JsonIgnore
    @OneToOne(mappedBy = "flightRoute")
    @ApiModelProperty(value = "Flight route's Flight")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name="ARRIVAL_AIRPORT_ID", nullable=false)
    @ApiModelProperty(value = "Arrival airport for the Flight")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name="DEPARTURE_AIRPORT_ID", nullable=false)
    @ApiModelProperty(value = "Departure airport for the flight")
    private Airport departureAirport;
}
