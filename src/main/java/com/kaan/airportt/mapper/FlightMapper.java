package com.kaan.airportt.mapper;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.dto.flight.FlightDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightRouteMapper.class, FlightTicketMapper.class, AirlineMapper.class, AirportMapper.class})
public interface FlightMapper {

    Flight toEntity(FlightDto dto);

    FlightDto toDto(Flight entity);
}
