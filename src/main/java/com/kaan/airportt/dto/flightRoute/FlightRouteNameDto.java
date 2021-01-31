package com.kaan.airportt.dto.flightRoute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FlightRouteNameDto {

    @NotBlank
    private String name;
}
