package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.airline.AirlineSaveDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.dto.airline.AirlineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, AirportMapper.class, FlightMapper.class})
public interface AirlineMapper {

    @Mapping(target = "flights", ignore = true)
    Airline toEntity(AirlineDto dto);

    @Mapping(target = "flights", ignore = true)
    AirlineDto toDto(Airline entity);

    Airline saveDtoToEntity(AirlineSaveDto airport);
}
