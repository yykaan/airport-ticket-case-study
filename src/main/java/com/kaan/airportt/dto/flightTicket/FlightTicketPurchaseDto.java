package com.kaan.airportt.dto.flightTicket;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlightTicketPurchaseDto {
    @NotNull
    private Long ticketId;

    private String creditCardNumber;
}
