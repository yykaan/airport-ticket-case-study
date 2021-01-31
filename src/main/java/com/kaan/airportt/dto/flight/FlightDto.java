package com.kaan.airportt.dto.flight;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.entity.FlightTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto extends BaseDto {

    private String name;

    private Airline airline;

    private Set<FlightTicket> flightTickets;

    private FlightRoute flightRoute;

    private Integer passengerCapacity;

    private transient String priceFormula;
}
