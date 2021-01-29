package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.Flight;
import com.kaan.finartz.airportticket.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends CommonService<Flight, FlightRepository>{

    public FlightService(FlightRepository repository) {
        super(repository);
    }
}
