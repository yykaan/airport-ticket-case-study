package com.kaan.airportt.repository;

import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.entity.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AirlineRepository extends CommonRepository<Airline> {

    List<Airline> findByName(String name);

    List<Airline> findByFlightsIn(Set<Flight> flights);
}
