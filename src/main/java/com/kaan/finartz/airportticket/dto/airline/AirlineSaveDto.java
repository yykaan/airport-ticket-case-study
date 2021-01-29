package com.kaan.finartz.airportticket.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineSaveDto {
    @NotBlank
    private String name;
}
