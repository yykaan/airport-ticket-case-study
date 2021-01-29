package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.FlightTicket;
import com.kaan.finartz.airportticket.repository.FlightTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightTicketService extends CommonService<FlightTicket, FlightTicketRepository> {

    public FlightTicketService(FlightTicketRepository repository) {
        super(repository);
    }
}
