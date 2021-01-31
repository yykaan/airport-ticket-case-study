package com.kaan.airportt.dto.airport;

import com.kaan.airportt.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto extends BaseDto {

    @NotBlank
    private String name;
}
