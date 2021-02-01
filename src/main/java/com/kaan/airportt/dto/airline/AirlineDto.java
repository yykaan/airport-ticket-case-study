package com.kaan.airportt.dto.airline;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.dto.flight.FlightDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AirlineDTO Documentation", description = "DTO")
public class AirlineDto extends BaseDto {

    @NotBlank
    @ApiModelProperty(value = "Airline's name field")
    private String name;

    @ApiModelProperty(value = "Airline's Flight list")
    private Set<FlightDto> flights;
}
