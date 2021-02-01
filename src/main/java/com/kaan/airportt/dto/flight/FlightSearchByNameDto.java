package com.kaan.airportt.dto.flight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "FlightSearchDTO documentation", description = "Helper DTO for searching Flights by name field")
public class FlightSearchByNameDto {
    @NotBlank
    @ApiModelProperty(value = "Flight's name")
    private String name;
}
