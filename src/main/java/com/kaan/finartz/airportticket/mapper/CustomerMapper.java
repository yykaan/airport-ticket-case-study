package com.kaan.finartz.airportticket.mapper;

import com.kaan.finartz.airportticket.dto.customer.CustomerDto;
import com.kaan.finartz.airportticket.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, FlightTicketMapper.class})
public interface CustomerMapper {

    Customer toEntity(CustomerDto dto);

    CustomerDto toDto(Customer entity);
}
