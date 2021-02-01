package com.kaan.airportt.dto.flight;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.dto.airline.AirlineDto;
import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlightDTO documentation", description = "DTO")
public class FlightDto extends BaseDto {

    @ApiModelProperty(value = "Flight's name field")
    private String name;

    @ApiModelProperty(value = "Airline that creates the Flight")
    private AirlineDto airline;

    @ApiModelProperty(value = "Flight's available ticket")
    private Set<FlightTicketDto> flightTickets;

    @ApiModelProperty(value = "Flight route of the flight")
    private FlightRouteDto flightRoute;

    @ApiModelProperty(value = "Maximum number of passenger, Flight Tickets created based on this field")
    private Integer passengerCapacity;

    @ApiModelProperty(value = "Base price of each tickets")
    private BigDecimal basePrice;
}
