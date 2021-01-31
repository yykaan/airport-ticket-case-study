package com.kaan.airportt.dto.flightRoute;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.entity.Flight;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightRouteDto extends BaseDto {

    private Flight flight;

    private Airport arrivalAirport;

    private Airport departureAirport;
}
