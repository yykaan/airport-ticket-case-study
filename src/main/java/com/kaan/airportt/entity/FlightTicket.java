package com.kaan.airportt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "FLIGHT_TICKET")
public class FlightTicket extends BaseEntity{

    @NotBlank
    @Column(name = "TICKET_NO")
    private String ticketNo;

    @ManyToOne
    @JoinColumn(name="FLIGHT_ID", nullable=false)
    private Flight flight;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IS_PURCHASED")
    private Boolean purchased;
}
