package com.kaan.finartz.airportticket.repository;

import com.kaan.finartz.airportticket.entity.Flight;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CommonRepository<Flight> {
}
