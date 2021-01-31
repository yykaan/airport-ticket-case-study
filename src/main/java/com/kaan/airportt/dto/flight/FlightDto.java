package com.kaan.airportt.dto.flight;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.entity.FlightTicket;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto extends BaseDto {

    private String name;

    private Airline airline;

    private Set<FlightTicket> flightTickets;

    private FlightRoute flightRoute;

    private Integer passengerCapacity;

    private BigDecimal basePrice;
}
