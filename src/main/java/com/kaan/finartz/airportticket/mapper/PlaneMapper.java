package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.plane.PlaneDto;
import com.kaan.finartz.airportticket.entity.Plane;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightMapper.class})
public interface PlaneMapper {
    Plane toEntity(PlaneDto dto);

    PlaneDto toDto(Plane entity);
}
