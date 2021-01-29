package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.flightTicket.FlightTicketDto;
import com.kaan.finartz.airportticket.entity.FlightTicket;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightMapper.class})
public interface FlightTicketMapper {
    FlightTicket toEntity(FlightTicketDto dto);

    FlightTicketDto toDto(FlightTicket entity);
}
