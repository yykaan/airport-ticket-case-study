package com.kaan.airportt.dto.airline;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDto extends BaseDto {

    @NotBlank
    private String name;

    private Set<Flight> flights;
}
