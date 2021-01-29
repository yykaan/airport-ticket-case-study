package com.kaan.finartz.airportticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {

    private Long id;

    private OffsetDateTime createdDate;

    private OffsetDateTime updatedDate;

    private Integer version;
}
