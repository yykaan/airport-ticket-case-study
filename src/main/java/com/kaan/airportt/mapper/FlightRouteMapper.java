package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.FlightRoute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, FlightMapper.class, AirportMapper.class})
public interface FlightRouteMapper {

    @Mapping(target = "flight", ignore = true)
    FlightRoute toEntity(FlightRouteDto dto);

    @Mapping(target = "flight", ignore = true)
    FlightRouteDto toDto(FlightRoute entity);
}
