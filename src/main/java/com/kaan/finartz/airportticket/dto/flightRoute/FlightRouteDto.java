package com.kaan.finartz.airportticket.dto.flightRoute;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.airport.AirportDto;
import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRouteDto extends BaseDto {

    @NotNull
    private FlightDto flight;

    @NotNull
    private AirportDto departureAirport;

    @NotNull
    private AirportDto arrivalAirport;

    @NotNull
    private OffsetDateTime deploymentDate;

    @NotNull
    private OffsetDateTime arrivalDate;

    private Double estimatedFlightTime;
}
