package com.kaan.airportt.dto.airline;

import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.entity.Flight;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDto extends BaseDto {

    @NotBlank
    private String name;

    private Set<Flight> flights;
}
