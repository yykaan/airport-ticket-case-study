package com.kaan.finartz.airportticket.dto.plane;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaneDto extends BaseDto {

    private Set<FlightDto> flight;

    @NotNull
    private Integer passengerCapacity;
}
