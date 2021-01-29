package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.flightRoute.FlightRouteDto;
import com.kaan.finartz.airportticket.entity.FlightRoute;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightMapper.class, AirportMapper.class})
public interface FlightRouteMapper {
    FlightRoute toEntity(FlightRouteDto dto);

    FlightRouteDto toDto(FlightRoute entity);
}
