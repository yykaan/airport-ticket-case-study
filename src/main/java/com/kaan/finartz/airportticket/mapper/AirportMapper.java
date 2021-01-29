package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.AirportDto;
import com.kaan.finartz.airportticket.entity.Airport;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DateMapper.class})
public interface AirportMapper {

    Airport toEntity(AirportDto dto);

    AirportDto toDto(Airport entity);
}
