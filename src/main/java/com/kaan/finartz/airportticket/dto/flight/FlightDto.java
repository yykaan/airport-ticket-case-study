package com.kaan.finartz.airportticket.dto.flight;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.flightRoute.FlightRouteDto;
import com.kaan.finartz.airportticket.dto.flightTicket.FlightTicketDto;
import com.kaan.finartz.airportticket.dto.plane.PlaneDto;
import com.kaan.finartz.airportticket.dto.airline.AirlineDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto extends BaseDto {

    @NotNull
    private Set<PlaneDto> plane;

    private FlightRouteDto flightRoute;

    private List<FlightTicketDto> flightTicketList;

    private AirlineDto airline;

    public void addFlightTicket(FlightTicketDto flightTicket){
        if (flightTicketList.isEmpty()){
            flightTicketList = new ArrayList<>();
        }
        flightTicketList.add(flightTicket);
        flightTicket.setFlight(this);
    }

    public void removeFlightTicket(FlightTicketDto flightTicket){
        if (flightTicketList.isEmpty()){
            flightTicketList = new ArrayList<>();
        }else {
            flightTicketList.remove(flightTicket);
            flightTicket.setFlight(null);
        }
    }
}
