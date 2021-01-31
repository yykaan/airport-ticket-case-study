package com.kaan.airportt.dto.flight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FlightSearchByNameDto {
    @NotBlank
    private String name;
}
