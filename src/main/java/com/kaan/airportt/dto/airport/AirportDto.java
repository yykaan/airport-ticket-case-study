package com.kaan.airportt.dto.airport;

import com.kaan.airportt.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AirportDTO documentation", description = "DTO")
public class AirportDto extends BaseDto {

    @NotBlank
    @ApiModelProperty(value = "Airport's name field")
    private String name;
}
