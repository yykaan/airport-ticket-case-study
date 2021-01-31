package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.airline.AirlineSaveDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.dto.airline.AirlineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, AirportMapper.class, FlightMapper.class})
public interface AirlineMapper {

    Airline toEntity(AirlineDto dto);

    AirlineDto toDto(Airline entity);

    Airline saveDtoToEntity(AirlineSaveDto airport);
}
