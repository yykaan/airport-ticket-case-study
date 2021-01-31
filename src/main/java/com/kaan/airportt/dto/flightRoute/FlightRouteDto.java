package com.kaan.airportt.dto.flightRoute;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRouteDto extends BaseDto {

    private Flight flight;

    private Airport arrivalAirport;

    private Airport departureAirport;
}
