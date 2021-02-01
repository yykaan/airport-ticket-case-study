package com.kaan.airportt.dto.airline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AirlineSaveDTO Documentation", description = "In order to save airport with given name but without flights")
public class AirlineSaveDto {

    @NotBlank
    @ApiModelProperty(value = "Airline's name field")
    private String name;
}
