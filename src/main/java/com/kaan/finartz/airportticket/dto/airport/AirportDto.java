package com.kaan.finartz.airportticket.dto.airport;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.airline.AirlineDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto extends BaseDto {

    @NotBlank
    private String name;

    private List<AirlineDto> airlines;
}
