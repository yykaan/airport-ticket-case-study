package com.kaan.airportt.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "FLIGHT")
@ApiModel(value = "Flight Entity Documentation", description = "Entity")
public class Flight extends BaseEntity {

    @NotBlank
    @Column(name = "NAME")
    @ApiModelProperty(value = "Flight's name field")
    private String name;

    @ManyToOne
    @JoinColumn(name="AIRLINE_ID", nullable=false)
    @ApiModelProperty(value = "Airline that creates the Flight")
    private Airline airline;

    @OneToMany(mappedBy="flight")
    @ApiModelProperty(value = "Flight's available ticket")
    private Set<FlightTicket> flightTickets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ROUTE_ID", referencedColumnName = "ID")
    @ApiModelProperty(value = "Flight route of the flight")
    private FlightRoute flightRoute;

    @Column(name = "PASSENGER_CAPACITY")
    @ApiModelProperty(value = "Maximum number of passenger, Flight Tickets created based on this field")
    private Integer passengerCapacity;

    @Column(name = "BASE_PRICE")
    @ApiModelProperty(value = "Base price of each tickets")
    private BigDecimal basePrice;

}
