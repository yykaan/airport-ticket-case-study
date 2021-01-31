package com.kaan.airportt.mapper;

import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import com.kaan.airportt.entity.FlightTicket;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightMapper.class})
public interface FlightTicketMapper {

    FlightTicket toEntity(FlightTicketDto dto);

    FlightTicketDto toDto(FlightTicket entity);
}
