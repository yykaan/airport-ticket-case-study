package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "FLIGHT_TICKET")
public class FlightTicket extends BaseEntity{

    @Column(name = "TICKET_NO")
    private Integer ticketNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="FLIGHT_ID", nullable=false)
    private Flight flight;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IS_PURCHASED")
    private boolean purchased = false;
}
