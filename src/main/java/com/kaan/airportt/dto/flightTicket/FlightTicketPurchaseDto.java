package com.kaan.airportt.dto.flightTicket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(value = "FlightTicketPurchaseDTO documentation", description = "Used for purchasing Flight Tickets with given ticket ID and Credit Card Number")
public class FlightTicketPurchaseDto {

    @NotNull
    @ApiModelProperty(value = "FLight Ticket's ID field for purchasing")
    private Long ticketId;

    @ApiModelProperty(value = "Credit Card number of client, will be masked by system automatically")
    private String creditCardNumber;
}
