package com.kaan.airportt.dto.flightTicket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class FlightTicketPurchaseDto {
    @NotNull
    private Long ticketId;

    private String creditCardNumber;
}
