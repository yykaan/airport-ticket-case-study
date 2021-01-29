package com.kaan.finartz.airportticket.dto.airline;

import com.kaan.finartz.airportticket.dto.airport.AirportDto;
import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDto extends BaseDto {

    @NotBlank
    private String name;

    private List<AirportDto> airports;

    private List<FlightDto> flightList;

    public void addFlight(FlightDto flight){
        if(flightList.isEmpty()){
            flightList = new ArrayList<>();
        }
        flightList.add(flight);
        flight.setAirline(this);
    }

    public void removeFlight(FlightDto flight){
        if(flightList.isEmpty()){
            flightList = new ArrayList<>();
        }else {
            flightList.remove(flight);
            flight.setAirline(null);
        }
    }
}
