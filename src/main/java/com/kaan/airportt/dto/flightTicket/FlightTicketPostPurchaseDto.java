package com.kaan.airportt.dto.flightTicket;

import com.kaan.airportt.dto.flight.FlightDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class FlightTicketPostPurchaseDto {
    private Integer ticketNo;

    private FlightDto flight;

    private BigDecimal price;

    private String creditCardNumber;
}
