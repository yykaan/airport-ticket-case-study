package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Table(name = "FLIGHT_TICKET")
@ApiModel(value = "FlightTicket Entity Documentation", description = "Entity")
public class FlightTicket extends BaseEntity{

    @Column(name = "TICKET_NO")
    @ApiModelProperty(value = "Flight Tickets' number, automatically created by the system as sequence")
    private Integer ticketNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="FLIGHT_ID", nullable=false)
    @ApiModelProperty(value = "Flight Tickets' Flight")
    private Flight flight;

    @Column(name = "PRICE")
    @ApiModelProperty(value = "Price for each ticket, price is automatically calculated based on Flight's sold ticket number")
    private BigDecimal price;

    @Column(name = "IS_PURCHASED")
    @ApiModelProperty(value = "Information about whether the ticket purchased or not")
    private boolean purchased = false;
}
