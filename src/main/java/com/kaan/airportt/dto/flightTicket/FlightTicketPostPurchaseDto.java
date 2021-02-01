package com.kaan.airportt.dto.flightTicket;

import com.kaan.airportt.dto.flight.FlightDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@ApiModel(value = "FlightTicketPostPurchaseDTO documentation", description = "Used after Flight Ticket purchase operation to show information about purchased Ticket")
public class FlightTicketPostPurchaseDto {

    @ApiModelProperty(value = "Flight Tickets' number, automatically created by the system as sequence")
    private Integer ticketNo;

    @ApiModelProperty(value = "Flight Tickets' Flight")
    private FlightDto flight;

    @ApiModelProperty(value = "Price for each ticket")
    private BigDecimal price;

    @ApiModelProperty(value = "Credit Card number of client, will be masked by system automatically")
    private String creditCardNumber;
}
