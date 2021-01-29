package com.kaan.finartz.airportticket.dto;

import com.kaan.finartz.airportticket.entity.Airline;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto extends BaseDto{

    @NotBlank
    private String name;

    private List<Airline> airlines;
}
