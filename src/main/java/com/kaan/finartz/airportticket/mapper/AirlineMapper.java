package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.airline.AirlineDto;
import com.kaan.finartz.airportticket.dto.airline.AirlineSaveDto;
import com.kaan.finartz.airportticket.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, AirportMapper.class, FlightMapper.class})
public interface AirlineMapper {

    Airline toEntity(AirlineDto dto);

    AirlineDto toDto(Airline entity);

    Airline saveDtoToEntity(AirlineSaveDto airport);
}
