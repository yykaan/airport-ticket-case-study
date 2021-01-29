package com.kaan.finartz.airportticket.repository;

import com.kaan.finartz.airportticket.entity.FlightTicket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends CommonRepository<FlightTicket> {
}
