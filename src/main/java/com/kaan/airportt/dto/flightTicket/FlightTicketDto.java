package com.kaan.airportt.dto.flightTicket;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightTicketDto extends BaseDto {

    private String ticketNo;

    private Flight flight;

    private BigDecimal price;

    private Boolean purchased;
}
