package com.kaan.airportt.dto.flightRoute;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.dto.airport.AirportDto;
import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.entity.Flight;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlightRouteDTO documentation", description = "DTO")
public class FlightRouteDto extends BaseDto {

    @ApiModelProperty(value = "Flight route's Flight")
    private FlightDto flight;

    @ApiModelProperty(value = "Arrival airport for the Flight")
    private AirportDto arrivalAirport;

    @ApiModelProperty(value = "Departure airport for the flight")
    private AirportDto departureAirport;
}
