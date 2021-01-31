package com.kaan.airportt.repository;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightRoute;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CommonRepository<Flight> {

    List<Flight> findByFlightRoute(FlightRoute flightRoute);

    List<Flight> findByName(String name);
}
