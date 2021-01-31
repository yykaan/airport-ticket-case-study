package com.kaan.airportt.service.airline;

import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.service.CommonService;

import java.util.List;

public interface AirlineService extends CommonService<Airline> {
    List<Airline> findByName(String name);

    List<Airline> findByFlightList(List<Flight> flightList);
}
