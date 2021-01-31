package com.kaan.airportt.dto.airport;

import com.kaan.airportt.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto extends BaseDto {

    @NotBlank
    private String name;
}
