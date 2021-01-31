package com.kaan.airportt.repository;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightTicketRepository extends CommonRepository<FlightTicket> {
    List<FlightTicket> findByFlight(Flight flight);

    FlightTicket findByIdAndPurchasedTrue(Long id);

    Long countByFlightAndPurchasedTrue(Flight flight);

    FlightTicket findFirstByPurchasedTrueOrderByUpdatedDateDesc();
}
