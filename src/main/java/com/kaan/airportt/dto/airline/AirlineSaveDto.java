package com.kaan.airportt.dto.airline;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirlineSaveDto {
    @NotBlank
    private String name;
}
