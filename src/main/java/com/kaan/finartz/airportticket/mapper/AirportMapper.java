package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.airport.AirportDto;
import com.kaan.finartz.airportticket.dto.airport.AirportSaveDto;
import com.kaan.finartz.airportticket.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface AirportMapper {

    @Mapping(target = "airlines", ignore = true)
    Airport toEntity(AirportDto dto);

    @Mapping(target = "airlines", ignore = true)
    AirportDto toDto(Airport entity);

    Airport saveDtoToEntity(AirportSaveDto airportSaveDto);
}
