package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import com.kaan.finartz.airportticket.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightRouteMapper.class, FlightTicketMapper.class, AirlineMapper.class})
public interface FlightMapper {
    Flight toEntity(FlightDto dto);

    FlightDto toDto(Flight entity);
}
