package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Where(clause = "DELETED = 0")
@Entity(name = "FlightTicket")
@Table(name = "FLIGHT_TICKET")
public class FlightTicket extends BaseEntity {
    public static final long serialVersionUID = 3006261589221775477L;

    @NotBlank
    @Column(name = "TICKET_NO")
    private String ticketNo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
}
