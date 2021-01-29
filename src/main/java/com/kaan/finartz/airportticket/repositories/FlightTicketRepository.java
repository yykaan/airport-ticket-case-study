package com.kaan.finartz.airportticket.repositories;

import com.kaan.finartz.airportticket.entity.FlightTicket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends PagingAndSortingRepository<FlightTicket, Long> {
}
