package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.FlightRoute;
import com.kaan.finartz.airportticket.repository.FlightRouteRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightRouteService extends CommonService<FlightRoute, FlightRouteRepository>{

    public FlightRouteService(FlightRouteRepository repository) {
        super(repository);
    }
}
