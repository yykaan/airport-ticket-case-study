package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import com.kaan.airportt.entity.FlightTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, FlightMapper.class})
public interface FlightTicketMapper {

    @Mapping(target = "flight", ignore = true)
    FlightTicket toEntity(FlightTicketDto dto);

    @Mapping(target = "flight", ignore = true)
    FlightTicketDto toDto(FlightTicket entity);
}
