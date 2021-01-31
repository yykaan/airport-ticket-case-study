package com.kaan.airportt.service.flight;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.service.CommonService;

import java.util.List;

public interface FlightService extends CommonService<Flight> {
    List<Flight> findByName(String name);

    List<Flight> findByFlightRoute(FlightRoute flightRoute);
}
