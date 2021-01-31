package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.airport.AirportDto;
import com.kaan.airportt.entity.Airport;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, AirlineMapper.class})
public interface AirportMapper {

    Airport toEntity(AirportDto dto);

    AirportDto toDto(Airport entity);
}
