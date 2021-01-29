package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Flight")
@Table(name = "FLIGHT")
public class Flight extends BaseEntity{
    public static final long serialVersionUID = 6518461855102691600L;

    @NotNull
    @JoinTable(name = "FLIGHT_PLANE_RELATION",
            joinColumns = @JoinColumn(name = "FLIGHT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLANE_ID"))
    @ManyToMany
    private Set<Plane> plane;

    @OneToOne(mappedBy = "flight", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private FlightRoute flightRoute;

    @OneToMany(
            mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FlightTicket> flightTicketList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airline airline;

    public void addFlightTicket(FlightTicket flightTicket){
        if (flightTicketList.isEmpty()){
            flightTicketList = new ArrayList<>();
        }
        flightTicketList.add(flightTicket);
        flightTicket.setFlight(this);
    }

    public void removeFlightTicket(FlightTicket flightTicket){
        if (flightTicketList.isEmpty()){
            flightTicketList = new ArrayList<>();
        }else {
            flightTicketList.remove(flightTicket);
            flightTicket.setFlight(null);
        }
    }

}
