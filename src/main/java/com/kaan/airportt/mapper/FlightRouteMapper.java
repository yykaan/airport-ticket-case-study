package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.FlightRoute;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightMapper.class, AirportMapper.class})
public interface FlightRouteMapper {

    FlightRoute toEntity(FlightRouteDto dto);

    FlightRouteDto toDto(FlightRoute entity);
}
